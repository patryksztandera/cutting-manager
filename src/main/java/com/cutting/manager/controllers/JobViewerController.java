package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.models.responses.MetalSheetFxModel;
import com.cutting.manager.models.services.JobService;
import com.cutting.manager.models.services.MetalSheetService;
import com.cutting.manager.models.services.TypeService;
import com.cutting.manager.singleton.JobSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
public class JobViewerController {
    @Value("classpath:/fxml/manager.fxml")
    private Resource managerResource;
    @Value("classpath:/fxml/jobList.fxml")
    private Resource jobListResource;
    private final ApplicationContext applicationContext;
    private final JobService jobService;
    private final MetalSheetService metalSheetService;
    private final TypeService typeService;
    @FXML
    public Button backButton;
    @FXML
    public Button doneButton;
    @FXML
    public ImageView imageView;
    @FXML
    public Label name;
    @FXML
    public Label length;
    @FXML
    public Label width;
    @FXML
    public Label thickness;
    @FXML
    public Label type;
    @FXML
    public Label info;
    @FXML
    public Label locationLabel;
    @FXML
    public Label quantityLabel;


    JobFxModel jobFxModel;
    MetalSheetFxModel metalSheetFxModel;

    public JobViewerController(ApplicationContext applicationContext, JobService jobService, MetalSheetService metalSheetService, TypeService typeService) {
        this.applicationContext = applicationContext;
        this.jobService = jobService;
        this.metalSheetService = metalSheetService;
        this.typeService = typeService;
    }

    public void initialize() {
        JobSingleton jobSingleton = JobSingleton.getINSTANCE();
        this.jobFxModel = jobSingleton.getJobFxModel();

        Image image = new Image(new ByteArrayInputStream(this.jobFxModel.getImgByte()));
        this.imageView.setImage(image);

        metalSheetFxModel = metalSheetService.getOne(this.jobFxModel.getMetalSheetId());
        labelsSettings();
        bindings();
    }

    private void bindings() {
        doneButton.disableProperty().set(true);
        if (metalSheetFxModel.getQuantity() > 0) {
            doneButton.disableProperty().set(false);
        }
    }

    private void labelsSettings() {
        this.name.setText(this.jobFxModel.getName());
        this.length.setText("Length: "+metalSheetFxModel.getLength());
        this.width.setText("Width: "+metalSheetFxModel.getWidth());
        this.thickness.setText("Thickness: "+metalSheetFxModel.getThickness());
        this.type.setText(metalSheetFxModel.getType());
        this.info.setText(typeService.getByType(metalSheetFxModel.getType()).getInfo() );
        this.locationLabel.setText(metalSheetFxModel.getLocation());
        this.quantityLabel.setText(String.valueOf(metalSheetFxModel.getQuantity()));
    }

    public void backToPreviousStage(ActionEvent actionEvent) throws IOException {
        BorderPane borderPane = (BorderPane) loadStage(managerResource);
        borderPane.setCenter(loadStage(jobListResource));
        Scene jobViewerScene = new Scene(borderPane);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(jobViewerScene);
        window.show();
    }

    private Parent loadStage(Resource resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }

    public void doneOnAction() {
        metalSheetService.updateQuantity(metalSheetFxModel.getId(), metalSheetFxModel.getQuantity() - 1);
        initialize();
        if (metalSheetFxModel.getQuantity() == 0) {
            jobService.setEndTime(jobFxModel.getId());
        }
    }
}

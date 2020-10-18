package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.singleton.JobSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    @FXML
    public Button backButton;
    @FXML
    public ImageView imageView;

    JobFxModel jobFxModel;

    public JobViewerController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void initialize() {
        JobSingleton jobSingleton = JobSingleton.getINSTANCE();
        this.jobFxModel = jobSingleton.getJobFxModel();

        Image image = new Image(new ByteArrayInputStream(this.jobFxModel.getImgByte()));
        this.imageView.setImage(image);
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
}

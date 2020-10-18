package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.models.services.JobService;
import com.cutting.manager.models.services.MetalSheetService;
import com.cutting.manager.singleton.JobSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JobListController {
    private final JobService jobService;
    private final MetalSheetService metalSheetService;
    @Value("classpath:/fxml/jobViewer.fxml")
    private Resource jobViewerResource;
    private final ApplicationContext applicationContext;
    @FXML
    public TableView<JobFxModel> jobTable;
    @FXML
    public TableColumn<JobFxModel, String> nameColumn;
    @FXML
    public TableColumn<JobFxModel, String> postTimeColumn;
    @FXML
    public TableColumn<JobFxModel, String> lengthColumn;
    @FXML
    public TableColumn<JobFxModel, String> widthColumn;
    @FXML
    public TableColumn<JobFxModel, String> thicknessColumn;
    @FXML
    public TableColumn<JobFxModel, String> typeColumn;
    @FXML
    public TableColumn<JobFxModel, String> locationColumn;
    @FXML
    public Button showButton;

    public JobListController(JobService jobService, MetalSheetService metalSheetService, ApplicationContext applicationContext) {
        this.jobService = jobService;
        this.metalSheetService = metalSheetService;
        this.applicationContext = applicationContext;
    }

    public void initialize() {
        tableSettings();
        passJobToDifferentController();
    }

    private void passJobToDifferentController() {
        JobSingleton jobSingleton = JobSingleton.getINSTANCE();

        this.jobTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            jobSingleton.setJobFxModel(observable.getValue());
        });
    }

    private void tableSettings() {
        this.jobTable.setItems(jobService.getUnfinishedJobs());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.postTimeColumn.setCellValueFactory(cellData -> cellData.getValue().postTimeProperty());
        this.lengthColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).lengthProperty().asString());
        this.widthColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).widthProperty().asString());
        this.thicknessColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).thicknessProperty().asString());
        this.typeColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).typeProperty());
        this.locationColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).locationProperty());
    }

    public void showOnAction(ActionEvent actionEvent) throws IOException {
        Scene jobViewerScene = new Scene(loadStage(jobViewerResource));

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

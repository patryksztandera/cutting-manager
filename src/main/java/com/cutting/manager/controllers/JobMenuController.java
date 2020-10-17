package com.cutting.manager.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JobMenuController {
    @Value("classpath:/fxml/job.fxml")
    private Resource jobResource;
    @Value("classpath:/fxml/jobList.fxml")
    private Resource jobListResource;
    @Value("classpath:/fxml/jobFinished.fxml")
    private Resource jobFinishedResource;
    private final ApplicationContext applicationContext;
    @FXML
    public Button newJobButton;
    @FXML
    public Button showButton;
    @FXML
    public Button finishedJobsButton;
    @FXML
    public AnchorPane anchorPane;

    public JobMenuController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void loadNewJob() throws IOException {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(loadStage(jobResource));
    }

    public void loadAllJobs() throws IOException {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(loadStage(jobListResource));
    }

    public void loadFinishedJobs() throws IOException {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(loadStage(jobFinishedResource));
    }

    private Parent loadStage(Resource resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(resource.getURL());
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        return fxmlLoader.load();
    }
}

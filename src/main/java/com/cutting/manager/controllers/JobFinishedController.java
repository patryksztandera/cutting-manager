package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.models.services.JobService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class JobFinishedController {
    private final JobService jobService;
    @FXML
    public TableView<JobFxModel> jobTable;
    @FXML
    public TableColumn<JobFxModel, String> nameColumn;
    @FXML
    public TableColumn<JobFxModel, String> postTimeColumn;
    @FXML
    public TableColumn<JobFxModel, String> doneColumn;

    public JobFinishedController(JobService jobService) {
        this.jobService = jobService;
    }

    public void initialize() {
        tableSettings();
    }

    private void tableSettings() {
        this.jobTable.setItems(jobService.getFinishedJobs());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.postTimeColumn.setCellValueFactory(cellData -> cellData.getValue().postTimeProperty());
        this.doneColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
    }
}

package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.models.services.JobService;
import com.cutting.manager.models.services.MetalSheetService;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class JobFinishedController {
    private final JobService jobService;
    private final MetalSheetService metalSheetService;
    @FXML
    public TableView<JobFxModel> jobTable;
    @FXML
    public TableColumn<JobFxModel, String> nameColumn;
    @FXML
    public TableColumn<JobFxModel, String> postTimeColumn;
    @FXML
    public TableColumn<JobFxModel, String> doneColumn;
    @FXML
    public TableColumn<JobFxModel, String> lengthColumn;
    @FXML
    public TableColumn<JobFxModel, String> widthColumn;
    @FXML
    public TableColumn<JobFxModel, String> thicknessColumn;
    @FXML
    public TableColumn<JobFxModel, String> typeColumn;

    public JobFinishedController(JobService jobService, MetalSheetService metalSheetService) {
        this.jobService = jobService;
        this.metalSheetService = metalSheetService;
    }

    public void initialize() {
        tableSettings();
    }

    private void tableSettings() {
        this.jobTable.setItems(jobService.getFinishedJobs());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.postTimeColumn.setCellValueFactory(cellData -> cellData.getValue().postTimeProperty());
        this.doneColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
        this.lengthColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).lengthProperty().asString());
        this.widthColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).widthProperty().asString());
        this.thicknessColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).thicknessProperty().asString());
        this.typeColumn.setCellValueFactory(cellData -> metalSheetService
                .getOne(cellData.getValue().getMetalSheetId()).typeProperty());
    }
}

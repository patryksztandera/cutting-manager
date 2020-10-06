package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.models.services.JobService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Component
public class JobController {
    private final JobService jobService;
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField pathTextField;
    @FXML
    public TextField metalSheetIdTextField;
    @FXML
    public Button addButton;
    @FXML
    public TableView<JobFxModel> jobTable;
    @FXML
    public TableColumn<JobFxModel, String> nameColumn;
    @FXML
    public TableColumn<JobFxModel, String> postTimeColumn;
    @FXML
    public TableColumn<JobFxModel, String> endTimeColumn;
    @FXML
    public ImageView imageView;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    public void initialize() {
        addBindings();
        tableSettings();
        imageViewSettings();
    }

    private void addBindings() {
        this.addButton.disableProperty().bind(nameTextField.textProperty().isEmpty()
                .or(pathTextField.textProperty().isEmpty())
                .or(metalSheetIdTextField.textProperty().isEmpty()));
    }

    private void tableSettings() {
        this.jobTable.setItems(jobService.getAll());
        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.postTimeColumn.setCellValueFactory(cellData -> cellData.getValue().postTimeProperty());
        this.endTimeColumn.setCellValueFactory(cellData -> cellData.getValue().endTimeProperty());
    }

    private void imageViewSettings() {
        this.jobTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            Image image = new Image(new ByteArrayInputStream(observable.getValue().getImgByte()));
            this.imageView.setImage(image);
        }));
    }

    public void addJob() {
        File file = new File(this.pathTextField.getText());
        try {
            jobService.add(new JobFxModel(
                    this.nameTextField.getText(),
                    FilenameUtils.getExtension(this.pathTextField.getText()),
                    Long.parseLong(this.metalSheetIdTextField.getText()),
                    FileUtils.readFileToByteArray(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialize();
    }
}

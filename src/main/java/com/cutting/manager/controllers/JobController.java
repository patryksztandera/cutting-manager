package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.JobFxModel;
import com.cutting.manager.models.responses.MetalSheetFxModel;
import com.cutting.manager.models.services.JobService;
import com.cutting.manager.models.services.MetalSheetService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class JobController {
    private final JobService jobService;
    private final MetalSheetService metalSheetService;
    @FXML
    public TextField nameTextField;
    @FXML
    public Button addButton;
    @FXML
    public Button fileChooserButton;
    @FXML
    public TextField pathTextField;
    @FXML
    public Label metalSheetLabel;
    @FXML
    public Button selectMetalSheetButton;
    @FXML
    public TableView<MetalSheetFxModel> metalSheetTable;
    @FXML
    public TableColumn<MetalSheetFxModel, Number> lengthColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, Number> widthColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, Number> thicknessColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, String> typeColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, String> ownerColumn;
    public CheckBox checkSheet;

    MetalSheetFxModel model;
    File file;

    public JobController(JobService jobService, MetalSheetService metalSheetService) {
        this.jobService = jobService;
        this.metalSheetService = metalSheetService;
    }

    public void initialize() {
        addBindings();
        tableSettings();
    }

    private void addBindings() {
        this.checkSheet.disableProperty().set(true);
        this.addButton.disableProperty().bind(nameTextField.textProperty().isEmpty()
                .or(pathTextField.textProperty().isEmpty()).or(this.checkSheet.selectedProperty().not()));
    }

    private void tableSettings() {
        this.metalSheetTable.setItems(metalSheetService.getAll());
        this.lengthColumn.setCellValueFactory(cellData -> cellData.getValue().lengthProperty());
        this.widthColumn.setCellValueFactory(cellData -> cellData.getValue().widthProperty());
        this.thicknessColumn.setCellValueFactory(cellData -> cellData.getValue().thicknessProperty());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.ownerColumn.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
    }

    public void fileOnAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.jpeg", "*.png"));
        this.file = fileChooser.showOpenDialog(null);
        pathTextField.setText(this.file.getAbsolutePath());
    }

    public void selectOnAction() {
        this.model = metalSheetTable.getSelectionModel().getSelectedItem();
        StringBuilder stringBuilder = new StringBuilder("Dimensions: " +
                model.getLength() + " x " + model.getWidth() + " x " + model.getThickness()
                + ", - " + model.getType() + " -");
        if (model.getOwner() != null)
            stringBuilder.append(" (").append(model.getOwner()).append(")");
        metalSheetLabel.setText(stringBuilder.toString());
        setCheckBox(true);
    }

    public void addJob() {
        try {
            jobService.add(new JobFxModel(
                    this.nameTextField.getText(),
                    FilenameUtils.getExtension(this.pathTextField.getText()),
                    this.model.getId(),
                    FileUtils.readFileToByteArray(this.file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.nameTextField.clear();
        this.pathTextField.clear();
        this.metalSheetLabel.setText("");
        setCheckBox(false);
        initialize();
    }

    private void setCheckBox(boolean b) {
        this.checkSheet.disableProperty().set(false);
        this.checkSheet.setSelected(b);
        this.checkSheet.disableProperty().set(true);
    }
}

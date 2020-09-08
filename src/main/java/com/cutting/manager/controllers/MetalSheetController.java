package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.MetalSheetFxModel;
import com.cutting.manager.models.services.LocationService;
import com.cutting.manager.models.services.MetalSheetService;
import com.cutting.manager.models.services.TypeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

@Component
public class MetalSheetController {
    private final MetalSheetService metalSheetService;
    private final TypeService typeService;
    private final LocationService locationService;
    @FXML
    public Button addButton;
    @FXML
    public TextField lengthTextField;
    @FXML
    public TextField widthTextField;
    @FXML
    public TextField thicknessTextField;
    @FXML
    public ComboBox<String> typeComboBox;
    @FXML
    public ComboBox<String> locationComboBox;
    @FXML
    public TableView<MetalSheetFxModel> metalSheetTable;
    @FXML
    public TableColumn<MetalSheetFxModel, String> timestamp;
    @FXML
    public TableColumn<MetalSheetFxModel, Number> lengthColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, Number> widthColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, Number> thicknessColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, String> typeColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, String> locationColumn;

    private String type;
    private String location;

    public MetalSheetController(MetalSheetService metalSheetService, TypeService typeService, LocationService locationService) {
        this.metalSheetService = metalSheetService;
        this.typeService = typeService;
        this.locationService = locationService;
    }

    public void initialize() {
        tableBindings();
        addBindings();
    }

    private void addBindings() {
        this.addButton.disableProperty().bind(
                this.lengthTextField.textProperty().isEmpty()
                .or(this.widthTextField.textProperty().isEmpty())
                .or(this.thicknessTextField.textProperty().isEmpty())
                .or(this.typeComboBox.valueProperty().isNull())
                .or(this.locationComboBox.valueProperty().isNull()));

        this.typeComboBox.setItems(typeService.getTypes());
        this.locationComboBox.setItems(locationService.getLocations());

    }

    private void tableBindings() {
        this.metalSheetTable.setItems(metalSheetService.getAll());
        this.timestamp.setCellValueFactory(cellData -> cellData.getValue().zonedDateTimeProperty());
        this.lengthColumn.setCellValueFactory(cellData -> cellData.getValue().lengthProperty());
        this.widthColumn.setCellValueFactory(cellData -> cellData.getValue().widthProperty());
        this.thicknessColumn.setCellValueFactory(cellData -> cellData.getValue().thicknessProperty());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    }

    public void addMetalSheet() {
        metalSheetService.add(new MetalSheetFxModel(Double.parseDouble(this.lengthTextField.getText()),
                Double.parseDouble(this.widthTextField.getText()),
                Double.parseDouble(this.thicknessTextField.getText()),
                this.type,
                this.location));
        this.lengthTextField.clear();
        this.widthTextField.clear();
        this.thicknessTextField.clear();
        initialize();
    }

    public void typeComboBoxOnAction() {
        this.type = this.typeComboBox.getSelectionModel().getSelectedItem();
    }

    public void locationComboBoxOnAction() {
        this.location = this.locationComboBox.getSelectionModel().getSelectedItem();
    }
}

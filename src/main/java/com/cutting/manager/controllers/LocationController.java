package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.LocationFxModel;
import com.cutting.manager.models.services.LocationService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LocationController {
    private final LocationService locationService;
    @FXML
    private TextField locationTextField;
    @FXML
    private Button addButton;
    @FXML
    public TableView<LocationFxModel> locationTable;
    @FXML
    private TableColumn<LocationFxModel, String> locationColumn;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    public void initialize() {
        tableBindings();
        this.addButton.disableProperty().bind(this.locationTextField.textProperty().isEmpty());
    }

    private void tableBindings() {
        this.locationTable.setItems(locationService.getAll());
        this.locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    }

    public void addLocation() {
        locationService.add(new LocationFxModel(this.locationTextField.getText()));
        this.locationTextField.clear();
        initialize();
    }
}

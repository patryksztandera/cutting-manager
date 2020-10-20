package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.MetalSheetFxModel;
import com.cutting.manager.models.services.LocationService;
import com.cutting.manager.models.services.MetalSheetService;
import com.cutting.manager.models.services.TypeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;
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
    public TextField quantityTextField;
    @FXML
    public TextField ownerTextField;
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
    @FXML
    public TableColumn<MetalSheetFxModel, Number> quantityColumn;
    @FXML
    public TableColumn<MetalSheetFxModel, String> ownerColumn;

    private String type;
    private String location;

    public MetalSheetController(MetalSheetService metalSheetService, TypeService typeService, LocationService locationService) {
        this.metalSheetService = metalSheetService;
        this.typeService = typeService;
        this.locationService = locationService;
    }

    public void initialize() {
        tableSettings();
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

    private void tableSettings() {
        this.metalSheetTable.setItems(metalSheetService.getAllExisting());
        this.timestamp.setCellValueFactory(cellData -> cellData.getValue().zonedDateTimeProperty());
        this.lengthColumn.setCellValueFactory(cellData -> cellData.getValue().lengthProperty());
        this.widthColumn.setCellValueFactory(cellData -> cellData.getValue().widthProperty());
        this.thicknessColumn.setCellValueFactory(cellData -> cellData.getValue().thicknessProperty());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
        this.quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        this.ownerColumn.setCellValueFactory(cellData -> cellData.getValue().ownerProperty());
        editTable();
    }

    private void editTable() {
        this.metalSheetTable.setEditable(true);
        this.lengthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        this.widthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        this.thicknessColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        this.quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
    }

    public void addMetalSheet() {
        String quantity;
        if (this.quantityTextField.textProperty().isNotEmpty().get()) {
            quantity = this.quantityTextField.getText();
        } else {
            quantity = "1";
        }
        MetalSheetFxModel model = new MetalSheetFxModel(Double.parseDouble(this.lengthTextField.getText()),
                Double.parseDouble(this.widthTextField.getText()),
                Double.parseDouble(this.thicknessTextField.getText()),
                Integer.parseInt(quantity),
                this.type,
                this.location);
        if (!this.ownerTextField.getText().isEmpty()) {
            model.setOwner(this.ownerTextField.getText());
        }
        metalSheetService.add(model);

        this.lengthTextField.clear();
        this.widthTextField.clear();
        this.thicknessTextField.clear();
        this.quantityTextField.clear();
        this.ownerTextField.clear();
        this.typeComboBox.setValue(null);
        this.locationComboBox.setValue(null);
        initialize();
    }

    public void typeComboBoxOnAction() {
        this.type = this.typeComboBox.getSelectionModel().getSelectedItem();
    }

    public void locationComboBoxOnAction() {
        this.location = this.locationComboBox.getSelectionModel().getSelectedItem();
    }

    public void deleteByContextMenu() {
        metalSheetService.deleteById(metalSheetTable.getSelectionModel().getSelectedItem().getId());
        initialize();
    }

    public void OnEditLength(TableColumn.CellEditEvent<MetalSheetFxModel, Number> metalSheetModelStringEvent) {
        metalSheetService.updateLength(metalSheetModelStringEvent.getRowValue().getId(),
                metalSheetModelStringEvent.getNewValue().doubleValue());
    }

    public void onEditWidth(TableColumn.CellEditEvent<MetalSheetFxModel, Number> metalSheetFxModelStringCellEditEvent) {
        metalSheetService.updateWidth(metalSheetFxModelStringCellEditEvent.getRowValue().getId(),
                metalSheetFxModelStringCellEditEvent.getNewValue().doubleValue());
    }

    public void onEditThickness(TableColumn.CellEditEvent<MetalSheetFxModel, Number> metalSheetFxModelStringCellEditEvent) {
        metalSheetService.updateThickness(metalSheetFxModelStringCellEditEvent.getRowValue().getId(),
                metalSheetFxModelStringCellEditEvent.getNewValue().doubleValue());
    }

    public void onEditQuantity(TableColumn.CellEditEvent<MetalSheetFxModel, Number> metalSheetFxModelStringCellEditEvent) {
        metalSheetService.updateQuantity(metalSheetFxModelStringCellEditEvent.getRowValue().getId(),
                metalSheetFxModelStringCellEditEvent.getNewValue().intValue());
    }
}

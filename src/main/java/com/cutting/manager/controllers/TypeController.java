package com.cutting.manager.controllers;

import com.cutting.manager.models.responses.TypeFxModel;
import com.cutting.manager.models.services.TypeService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import org.springframework.stereotype.Component;

@Component
public class TypeController {
    private final TypeService typeService;
    @FXML
    public TextField typeTextField;
    @FXML
    public TextField infoTextField;
    @FXML
    public Button addButton;
    @FXML
    public TableView<TypeFxModel> typeTable;
    @FXML
    public TableColumn<TypeFxModel, String> typeColumn;
    @FXML
    public TableColumn<TypeFxModel, String> infoColumn;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    public void initialize() {
        tableBindings();
        this.addButton.disableProperty().bind(this.typeTextField.textProperty().isEmpty());
    }

    private void tableBindings() {
        this.typeTable.setItems(typeService.getAll());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.infoColumn.setCellValueFactory(cellData -> cellData.getValue().infoProperty());
        editTable();
    }

    private void editTable() {
        this.typeTable.setEditable(true);
        this.typeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.infoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void addType() {
        this.typeService.add(new TypeFxModel(this.typeTextField.getText(), this.infoTextField.getText()));
        this.typeTextField.clear();
        this.infoTextField.clear();
        initialize();
    }

    public void deleteByContextMenu() {
        typeService.delete(typeTable.getSelectionModel().getSelectedItem().getType());
        initialize();
    }

    public void onEditType(TableColumn.CellEditEvent<TypeFxModel, String> typeFxModelStringCellEditEvent) {
        typeService.updateType(
                typeFxModelStringCellEditEvent.getOldValue(),
                typeFxModelStringCellEditEvent.getNewValue());
    }

    public void onEditInfo(TableColumn.CellEditEvent<TypeFxModel, String> typeFxModelStringCellEditEvent) {
        typeService.updateInfo(
                typeFxModelStringCellEditEvent.getRowValue().getType(),
                typeFxModelStringCellEditEvent.getNewValue());
    }
}

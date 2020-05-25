package manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import manager.database.SheetDao;
import manager.models.SheetModel;

public class SheetController {

    @FXML
    private TableView<SheetModel> sheetTable;

    @FXML
    private TableColumn<SheetModel, Number> idSheetColumn;

    @FXML
    private TableColumn<SheetModel, String> timeColumn;

    @FXML
    private TableColumn<SheetModel, Number> lengthColumn;

    @FXML
    private TableColumn<SheetModel, Number> widthColumn;

    @FXML
    private TableColumn<SheetModel, Number> thicknessColumn;

    @FXML
    private TableColumn<SheetModel, String> typeSheetColumn;

    @FXML
    private TableColumn<SheetModel, String> locationSheetColumn;

    private SheetDao sheetDao;

    public void initialize() {
        this.sheetDao = new SheetDao();
        sheetDao.selectAll();
        bindingsTableView();
    }

    private void bindingsTableView() {
        this.sheetTable.setItems(this.sheetDao.getSheetModelObservableList());
        this.idSheetColumn.setCellValueFactory(cellData -> cellData.getValue().idSheetProperty());
        this.timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());
        this.lengthColumn.setCellValueFactory(cellData -> cellData.getValue().lengthProperty());
        this.widthColumn.setCellValueFactory(cellData -> cellData.getValue().widthProperty());
        this.thicknessColumn.setCellValueFactory(cellData -> cellData.getValue().thicknessProperty());
        this.typeSheetColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.locationSheetColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    }


}

package manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import manager.database.LocationDao;
import manager.model.LocationModel;

public class LocationController {

    @FXML
    private TableView<LocationModel> locationTable;

    @FXML
    private TableColumn<LocationModel,Number> idColumn;

    @FXML
    private TableColumn<LocationModel,String> locationColumn;

    private LocationDao locationDao;

    public void initialize(){
        this.locationDao = new LocationDao();
        locationDao.selectAll();
        bindingsTableView();
    }

    private void bindingsTableView() {
        this.locationTable.setItems(this.locationDao.getLocationModelObservableList());
        this.idColumn.setCellValueFactory(cellData -> cellData.getValue().idLocationProperty());
        this.locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    }
}

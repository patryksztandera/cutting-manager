package manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import manager.database.LocationDao;
import manager.models.LocationModel;

public class LocationController {

    @FXML
    private TextField locationAddTextField;

    @FXML
    private Button locationAddButton;

    @FXML
    private TableView<LocationModel> locationTable;

    @FXML
    private TableColumn<LocationModel, Number> idColumn;

    @FXML
    private TableColumn<LocationModel, String> locationColumn;

    private LocationDao locationDao;

    public void initialize() {
        this.locationDao = new LocationDao();
        locationDao.selectAll();
        this.locationAddButton.disableProperty().bind(this.locationAddTextField.textProperty().isEmpty());
        bindingsTableView();
    }

    private void bindingsTableView() {
        this.locationTable.setItems(this.locationDao.getLocationModelObservableList());
        this.idColumn.setCellValueFactory(cellData -> cellData.getValue().idLocationProperty());
        this.locationColumn.setCellValueFactory(cellData -> cellData.getValue().locationProperty());
    }

    public void addLocationAction(){
        locationDao.insert(this.locationAddTextField.getText());
        this.locationAddTextField.clear();
        initialize();
    }
}

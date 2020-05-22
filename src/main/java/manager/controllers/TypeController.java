package manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import manager.database.TypeDao;
import manager.models.TypeModel;

public class TypeController {

    @FXML
    private TableView<TypeModel> typeTable;

    @FXML
    private TableColumn<TypeModel,Number> idTypeColumn;

    @FXML
    private TableColumn<TypeModel,String> typeColumn;

    @FXML
    private TableColumn<TypeModel,String> infoColumn;

    private TypeDao typeDao;

    public void initialize() {
        this.typeDao = new TypeDao();
        typeDao.selectAll();
        bindingsTableView();
    }

    private void bindingsTableView() {
        this.typeTable.setItems(this.typeDao.getTypeModelObservableList());
        this.idTypeColumn.setCellValueFactory(cellData -> cellData.getValue().idTypeProperty());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.infoColumn.setCellValueFactory(cellData -> cellData.getValue().infoProperty());
    }
}

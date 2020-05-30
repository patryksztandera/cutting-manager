package manager.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import manager.database.TypeDao;
import manager.models.TypeModel;

public class TypeController {

    @FXML
    private TextField typeTextField;

    @FXML
    private TextArea infoTextArea;

    @FXML
    private Button addTypeButton;

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
        bindingsAdd();
        bindingsTableView();
    }

    private void bindingsAdd() {
        this.typeDao.typeModelProperty().get().typeProperty().bind(this.typeTextField.textProperty());
        this.typeDao.typeModelProperty().get().infoProperty().bind(this.infoTextArea.textProperty());

        this.addTypeButton.disableProperty().bind(this.typeTextField.textProperty().isEmpty());
    }

    private void bindingsTableView() {
        this.typeTable.setItems(this.typeDao.getTypeModelObservableList());
        this.idTypeColumn.setCellValueFactory(cellData -> cellData.getValue().idTypeProperty());
        this.typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        this.infoColumn.setCellValueFactory(cellData -> cellData.getValue().infoProperty());
    }

    public void addButtonOnAction() {
        typeDao.insert();
        this.typeTextField.clear();
        this.infoTextArea.clear();
        initialize();
    }
}

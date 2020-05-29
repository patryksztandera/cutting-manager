package manager.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import manager.database.LocationDao;
import manager.database.SheetDao;
import manager.database.TypeDao;
import manager.models.LocationModel;
import manager.models.SheetModel;
import manager.models.TypeModel;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SheetController {

    @FXML
    private TextField lengthTextField;

    @FXML
    private TextField widthTextField;

    @FXML
    private TextField thicknessTextField;

    @FXML
    private ComboBox<TypeModel> typeComboBox;

    @FXML
    private ComboBox<LocationModel> locationComboBox;

    @FXML
    private Button addButton;

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
    private TypeDao typeDao;
    private LocationDao locationDao;

    public void initialize() {
        this.sheetDao = new SheetDao();
        this.typeDao = new TypeDao();
        this.locationDao = new LocationDao();
        sheetDao.selectAll();
        typeDao.selectAll();
        locationDao.selectAll();
        bindingsAdd();
        bindingsTableView();
    }

    private void bindingsAdd() {
        this.lengthTextField.setTextFormatter(formatTextFieldToDouble());
        this.widthTextField.setTextFormatter(formatTextFieldToDouble());
        this.thicknessTextField.setTextFormatter(formatTextFieldToDouble());

        this.typeComboBox.setItems(this.typeDao.getTypeModelObservableList());
        this.locationComboBox.setItems(this.locationDao.getLocationModelObservableList());
        this.sheetDao.sheetModelProperty().get().lengthProperty().bind(convert(this.lengthTextField));
        this.sheetDao.sheetModelProperty().get().widthProperty().bind(convert(this.widthTextField));
        this.sheetDao.sheetModelProperty().get().thicknessProperty().bind(convert(this.thicknessTextField));

        this.addButton.disableProperty().bind(this.lengthTextField.textProperty().isEqualTo("0")
                .or(this.lengthTextField.textProperty().isEmpty())
                .or(this.widthTextField.textProperty().isEqualTo("0"))
                .or(this.widthTextField.textProperty().isEmpty())
                .or(this.thicknessTextField.textProperty().isEqualTo("0"))
                .or(this.thicknessTextField.textProperty().isEmpty())
                .or(this.locationComboBox.valueProperty().isNull())
                .or(this.typeComboBox.valueProperty().isNull()));
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

    public void onActionTypeComboBox(){
        this.sheetDao.setTypeModelObjectProperty(this.typeComboBox.getSelectionModel().getSelectedItem());
    }

    public void onActionLocationComboBox(){
        this.sheetDao.setLocationModelObjectProperty(this.locationComboBox.getSelectionModel().getSelectedItem());
    }

    public void onActionAddButton() {
        sheetDao.insertModel();
        initialize();
    }

    public TextFormatter<Double> formatTextFieldToDouble(){
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>()  {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || s.equals(".")) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };
        return new TextFormatter<>(converter, 0.0, filter);
    }

    public DoubleProperty convert(TextField textField) {
        DoubleProperty doubleProperty = new SimpleDoubleProperty();
        Bindings.bindBidirectional(textField.textProperty(), doubleProperty, new NumberStringConverter());
        return doubleProperty;
    }
}

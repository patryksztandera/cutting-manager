package manager.database;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import manager.models.LocationModel;
import manager.models.SheetModel;
import manager.models.TypeModel;
import manager.utils.DatabaseUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.function.Predicate;

public class SheetDao {

    private Dao dml = new Dao();

    private ObservableList<SheetModel> sheetModelObservableList = FXCollections.observableArrayList();
    private ObservableList<Double> widthObservableList = FXCollections.observableArrayList();
    private ObservableList<Double> thicknessObservableList = FXCollections.observableArrayList();

    private HashSet<Double> widthHashSet = new HashSet<>();
    private HashSet<Double> thicknessHashSet = new HashSet<>();

    private ObjectProperty<SheetModel> sheetModelObjectProperty = new SimpleObjectProperty<>(new SheetModel());
    private ObjectProperty<LocationModel> locationModelObjectProperty = new SimpleObjectProperty<>(new LocationModel());
    private ObjectProperty<TypeModel> typeModelObjectProperty = new SimpleObjectProperty<>(new TypeModel());

    private ObjectProperty<Double> widthObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Double> thicknessObjectProperty = new SimpleObjectProperty<>();

    public void insertModel() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        dml.dataManipulation("INSERT INTO sheet VALUES (NULL,'" +
                timestamp + "','" +
                this.sheetModelObjectProperty.get().lengthProperty().getValue() + "','" +
                this.sheetModelObjectProperty.get().widthProperty().getValue() + "','" +
                this.sheetModelObjectProperty.get().thicknessProperty().getValue() + "','" +
                this.locationModelObjectProperty.get().idLocationProperty().getValue() + "','" +
                this.typeModelObjectProperty.get().idTypeProperty().getValue() + "');");
    }

    public void delete(double id) {
        dml.dataManipulation("DELETE FROM sheet WHERE id_sheet='" + id + "';");
    }


    public void selectAll() {
        try {
            Connection conn = DatabaseUtils.sqlConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sheet");

            conn.setAutoCommit(false);

            conn.commit();

            while (rs.next()) {

                TypeDao typeDao = new TypeDao();
                typeDao.selectType(rs.getInt("id_type"));

                LocationDao locationDao = new LocationDao();
                locationDao.selectLocation(rs.getInt("id_location"));

                SheetModel sheetModel = new SheetModel();

                sheetModel.setIdSheet(rs.getInt("id_sheet"));

                String time = "" + rs.getTimestamp("time");
                sheetModel.setTime(time);

                sheetModel.setLength(rs.getDouble("length"));

                double width = rs.getDouble("width");
                sheetModel.setWidth(width);

                double thickness = rs.getDouble("thickness");
                sheetModel.setThickness(thickness);

                sheetModel.setType(typeDao.typeModel.getType());
                sheetModel.setLocation(locationDao.locationModel.getLocation());

                this.sheetModelObservableList.add(sheetModel);
                this.thicknessHashSet.add(thickness);
                this.widthHashSet.add(width);
            }
            this.widthObservableList.addAll(this.widthHashSet);
            this.thicknessObservableList.addAll(this.thicknessHashSet);

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Predicate<SheetModel> widthPredicate() {
        return sheetModel -> sheetModel.getWidth() == widthObjectProperty.get();
    }

    private Predicate<SheetModel> thicknessPredicate() {
        return sheetModel -> sheetModel.getThickness() == thicknessObjectProperty.get();
    }

    public FilteredList<SheetModel> filterWidth (){
        FilteredList<SheetModel> filteredList = new FilteredList<>(this.sheetModelObservableList, p -> true);
        filteredList.setPredicate(widthPredicate());
        return filteredList;
    }
    public FilteredList<SheetModel> filterThickness (){
        FilteredList<SheetModel> filteredList = new FilteredList<>(this.sheetModelObservableList, p -> true);
        filteredList.setPredicate(thicknessPredicate());
        return filteredList;
    }
    public FilteredList<SheetModel> filter (){
        FilteredList<SheetModel> filteredList = new FilteredList<>(this.sheetModelObservableList, p -> true);

        if(widthProperty() != null && thicknessProperty() != null) {
            filteredList.setPredicate(thicknessPredicate().and(widthPredicate()));
        }
        else if (widthProperty() != null && thicknessProperty() == null ) {
            filteredList.setPredicate(widthPredicate());
        }
        else if (thicknessProperty() != null && widthProperty() ==null) {
            filteredList.setPredicate(thicknessPredicate());
        }
        else {
            filteredList.setPredicate(null);
        }

        return filteredList;
    }

    public ObservableList<SheetModel> getSheetModelObservableList() {
        return sheetModelObservableList;
    }

    public SheetModel getSheetModelObjectProperty() {
        return sheetModelObjectProperty.get();
    }

    public ObjectProperty<SheetModel> sheetModelProperty() {
        return sheetModelObjectProperty;
    }

    public void setSheetModelObjectProperty(SheetModel sheetModelObjectProperty) {
        this.sheetModelObjectProperty.set(sheetModelObjectProperty);
    }

    public LocationModel getLocationModelObjectProperty() {
        return locationModelObjectProperty.get();
    }

    public ObjectProperty<LocationModel> locationModelProperty() {
        return locationModelObjectProperty;
    }

    public void setLocationModelObjectProperty(LocationModel locationModelObjectProperty) {
        this.locationModelObjectProperty.set(locationModelObjectProperty);
    }

    public TypeModel getTypeModelObjectProperty() {
        return typeModelObjectProperty.get();
    }

    public ObjectProperty<TypeModel> typeModelProperty() {
        return typeModelObjectProperty;
    }

    public void setTypeModelObjectProperty(TypeModel typeModelObjectProperty) {
        this.typeModelObjectProperty.set(typeModelObjectProperty);
    }

    public ObservableList<Double> getThicknessObservableList() {
        return thicknessObservableList;
    }

    public void setThicknessObservableList(ObservableList<Double> thicknessObservableList) {
        this.thicknessObservableList = thicknessObservableList;
    }

    public Double getThicknessObjectProperty() {
        return thicknessObjectProperty.get();
    }

    public ObjectProperty<Double> thicknessProperty() {
        return thicknessObjectProperty;
    }

    public void setThicknessObjectProperty(Double thicknessObjectProperty) {
        this.thicknessObjectProperty.set(thicknessObjectProperty);
    }

    public ObservableList<Double> getWidthObservableList() {
        return widthObservableList;
    }

    public void setWidthObservableList(ObservableList<Double> widthObservableList) {
        this.widthObservableList = widthObservableList;
    }

    public Double getWidthObjectProperty() {
        return widthObjectProperty.get();
    }

    public ObjectProperty<Double> widthProperty() {
        return widthObjectProperty;
    }

    public void setWidthObjectProperty(Double widthObjectProperty) {
        this.widthObjectProperty.set(widthObjectProperty);
    }
}

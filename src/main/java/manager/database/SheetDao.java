package manager.database;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manager.models.LocationModel;
import manager.models.SheetModel;
import manager.models.TypeModel;
import manager.utils.DatabaseUtils;

import java.sql.*;

public class SheetDao {

    private Dao dml = new Dao();

    private ObservableList<SheetModel> sheetModelObservableList = FXCollections.observableArrayList();
    private ObjectProperty<SheetModel> sheetModelObjectProperty = new SimpleObjectProperty<>(new SheetModel());
    private ObjectProperty<LocationModel> locationModelObjectProperty = new SimpleObjectProperty<>(new LocationModel());
    private ObjectProperty<TypeModel> typeModelObjectProperty = new SimpleObjectProperty<>(new TypeModel());

    public void insert(double length, double width, double thickness, int idLocation, int idType) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dml.dataManipulation("INSERT INTO sheet VALUES (NULL,'" +
                timestamp + "','" +
                length+ "','" +
                width + "','" +
                thickness + "','" +
                idLocation + "','" +
                idType + "');");
    }

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
                int idType = rs.getInt("id_type");
                int idLocation = rs.getInt("id_location");
                String type = "";
                String location = "";

                Statement typeStmt = conn.createStatement();
                ResultSet typeRs = typeStmt.executeQuery("SELECT type FROM type WHERE id_type='" + idType + "';");

                Statement locStmt = conn.createStatement();
                ResultSet locRs = locStmt.executeQuery("SELECT location FROM location WHERE id_location='" + idLocation + "';");

                while (typeRs.next()) {
                    type = typeRs.getString("type");
                }
                while (locRs.next()) {
                    location = locRs.getString("location");
                }

                SheetModel sheetModel = new SheetModel();
                sheetModel.setIdSheet(rs.getInt("id_sheet"));
                String time = "" + rs.getTimestamp("time");
                sheetModel.setTime(time);
                sheetModel.setLength(rs.getDouble("length"));
                sheetModel.setWidth(rs.getDouble("width"));
                sheetModel.setThickness(rs.getDouble("thickness"));
                sheetModel.setType(type);
                sheetModel.setLocation(location);
                this.sheetModelObservableList.add(sheetModel);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

}

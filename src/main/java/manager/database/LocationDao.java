package manager.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manager.models.LocationModel;
import manager.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDao {

    private Dao dml = new Dao();

    private ObservableList<LocationModel> locationModelObservableList = FXCollections.observableArrayList();

    public void insert(String location) {
        dml.dataManipulation("INSERT INTO location VALUES (NULL,'" + location + "');");
    }

    public void delete(double id) {
        dml.dataManipulation("DELETE FROM location WHERE id_location='" + id + "';");
    }

    public void selectAll() {

        try {
            Connection conn = DatabaseUtils.sqlConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM location");

            conn.setAutoCommit(false);

            conn.commit();

            while (rs.next()) {
                LocationModel locationModel = new LocationModel();
                locationModel.setIdLocation(rs.getInt("id_location"));
                locationModel.setLocation(rs.getString("location"));
                this.locationModelObservableList.add(locationModel);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<LocationModel> getLocationModelObservableList() {
        return locationModelObservableList;
    }

    public void setLocationModelObservableList(ObservableList<LocationModel> locationModelObservableList) {
        this.locationModelObservableList = locationModelObservableList;
    }
}


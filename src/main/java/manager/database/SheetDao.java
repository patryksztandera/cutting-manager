package manager.database;

import manager.models.SheetModel;
import manager.utils.DatabaseUtils;

import java.sql.*;

public class SheetDao {

    private Dao dml = new Dao();
    private double length;
    private double width;
    private double thickness;
    private int idLocation;
    private int idType;

    SheetDao() {
    }

    SheetDao(double length, double width, double thickness, int idLocation, int idType) {
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.idLocation = idLocation;
        this.idType = idType;
    }

    void insert() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dml.dataManipulation("INSERT INTO sheet VALUES (NULL,'" +
                timestamp + "','" +
                length + "','" +
                width + "','" +
                thickness + "','" +
                idLocation + "','" +
                idType + "');");
    }

    void delete(double id) {
        dml.dataManipulation("DELETE FROM sheet WHERE id_sheet='" + id + "';");
    }

    void selectAll() {
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
                      /*  + " : " + type
                        + " : " + location);*/
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

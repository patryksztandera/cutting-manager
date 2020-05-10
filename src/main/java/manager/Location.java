package manager;

import manager.database.DML;
import manager.utils.DatabaseUtils;

import java.sql.*;

public class Location {

    private DML dml = new DML();

    void insert(String location) {
        dml.dataManipulation("INSERT INTO location VALUES (NULL,'" + location + "');");
    }

    void delete(double id) {
        dml.dataManipulation("DELETE FROM location WHERE id_location='" + id + "';");
    }

    void selectAll() {

        try {
            Connection conn = DatabaseUtils.sqlConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM location");

            conn.setAutoCommit(false);

            conn.commit();

            while (rs.next()) {
                System.out.println(rs.getInt("id_location") + " : " + rs.getString("location"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


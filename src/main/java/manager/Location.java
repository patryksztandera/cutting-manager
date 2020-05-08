package manager;

import manager.database.DML;

import java.sql.*;

public class Location {

    private DML dml = new DML();
    void insert(String location) throws ClassNotFoundException{
        dml.dataManipulation("INSERT INTO location VALUES (NULL,'"+location+"');");
    }

    void delete(double id) throws ClassNotFoundException{
        dml.dataManipulation("DELETE FROM location WHERE id_location='"+id+"';");
    }

    void selectAll()throws SQLException, ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/management?serverTimezone=Europe/Warsaw",
                    "root",
                    "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM location");

            conn.setAutoCommit(false);

            conn.commit();

            while(rs.next()) {
                System.out.println(rs.getInt("id_location") + " : " + rs.getString("location"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


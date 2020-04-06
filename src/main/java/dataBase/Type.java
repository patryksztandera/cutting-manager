package dataBase;

import java.sql.*;

public class Type {

    void insertType(String type) throws SQLException, ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/management?serverTimezone=Europe/Warsaw",
                    "root",
                    "password");
            Statement stmt = conn.createStatement();

            conn.setAutoCommit(false);

            stmt.executeUpdate("INSERT INTO type VALUES (NULL,'"+
                    type+"',NULL);");

            conn.commit();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void delete(double id) throws SQLException, ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/management?serverTimezone=Europe/Warsaw",
                    "root",
                    "password");
            Statement stmt = conn.createStatement();

            conn.setAutoCommit(false);

            stmt.executeUpdate("DELETE FROM type WHERE id_type='"+id+"';");

            conn.commit();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void selectAll()throws SQLException, ClassNotFoundException{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/management?serverTimezone=Europe/Warsaw",
                    "root",
                    "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM type");

            conn.setAutoCommit(false);

            conn.commit();

            while(rs.next()) {
                System.out.println(rs.getInt("id_type")
                        + " : " + rs.getString("type")
                        + " , " + rs.getString("information"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


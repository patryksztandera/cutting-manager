package manager.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DML {

    public void dataManipulation(String sqlStatement) throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/management?serverTimezone=Europe/Warsaw",
                    "root",
                    "password");
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);

            stmt.executeUpdate(sqlStatement);

            conn.commit();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

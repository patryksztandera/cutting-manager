package manager.database;

import manager.utils.DatabaseUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

    public void dataManipulation(String sqlStatement) {
        try {
            Connection conn = DatabaseUtils.sqlConnection();

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

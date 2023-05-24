package proba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PrDBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public void DBWorker() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

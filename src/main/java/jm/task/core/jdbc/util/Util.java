package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/test?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            connection.commit();
            System.out.println("good connection!");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("no good connection!" + e);
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.err.println("no rollback connection!" + ex);
            }
        }
        return connection;
    }

}

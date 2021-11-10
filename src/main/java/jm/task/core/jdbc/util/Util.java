package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String dbUrl = "jdbc:mysql://localhost:3306/mysql?useSSL=false";
    private static final String dbUsername = "root";
    private static final String dbPassword = "root";

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        return connection;
    }
}

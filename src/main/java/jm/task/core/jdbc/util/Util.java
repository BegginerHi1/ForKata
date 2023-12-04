package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private String url = "jdbc:mysql://localhost:3306/base";
    private String user = "root";
    private String pass = "Rootroot1";
    private Connection connection;

    public Connection getConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}

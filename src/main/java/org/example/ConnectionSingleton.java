package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static Connection connection;
    private static final String name = "admin";
    private static final String password = "12345";

    public static Connection getConnection() throws SQLException {

        if(connection == null || connection.isClosed())
            connection = DriverManager.getConnection("", name, password);

        return connection;
    }
}

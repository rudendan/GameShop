package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "mypassword";

    public static Connection getConnection() {
        try {

            if (connection == null || connection.isClosed())
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gameshop", name, password);

            return connection;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}

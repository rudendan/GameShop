package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingletonTest {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "mypassword";
    private static final String pathDB = "jdbc:postgresql://localhost:5432/gameshoptest";

    public static Connection getConnection() {
        try {

            if (connection == null || connection.isClosed())
                connection = DriverManager.getConnection(pathDB, name, password);

            return connection;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
}

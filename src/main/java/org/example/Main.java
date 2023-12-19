package org.example;


import org.example.service.MenuService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String pathDB = "jdbc:postgresql://localhost:5432/gameshop";
        String pathSQL = "src/main/resources/init.sql";

        try (Connection connection = ConnectionSingleton.getConnection(pathDB)) {


            InitDB.runScript(connection, pathSQL); // create tables in db if they were not created
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");

            MenuService menuService = new MenuService(scanner, connection);
            menuService.run();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
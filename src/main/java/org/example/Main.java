package org.example;


import org.example.enums.MenuMessages;
import org.example.service.GameService;
import org.example.service.MenuService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConnectionSingleton.getConnection()) {


            InitDB.runScript(connection); // create tables in db if they were not created
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");

            MenuService menuService = new MenuService(scanner, connection);
            menuService.run();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
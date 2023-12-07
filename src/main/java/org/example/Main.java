package org.example;


import org.example.enums.MenuMessages;
import org.example.service.MenuService;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        Connection connection = ConnectionSingleton.getConnection();
        InitDB.runScript(connection); // create tables in db if they were not created
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        MenuService menuService = new MenuService(scanner, connection);
        System.out.println(MenuMessages.WELLCOME);


    }
}
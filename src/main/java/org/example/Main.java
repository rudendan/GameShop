package org.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){

        Connection connection = ConnectionSingleton.getConnection();
        InitDB.runScript(connection);


    }
}
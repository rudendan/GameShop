package org.example;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;

public class InitDB {

    private static final String path = "src/main/resources/init.sql";

    public static void runScript(Connection connection) {

        try {
            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.setSendFullScript(false);
            scriptRunner.setStopOnError(true);
            scriptRunner.runScript(new FileReader(path));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

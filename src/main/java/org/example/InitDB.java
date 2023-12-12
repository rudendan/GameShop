package org.example;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InitDB {

    private static final String path = "src/main/resources/init.sql";

    public static void runScript(Connection connection) {

        DatabaseMetaData metaData = null;
        ResultSet resultSet = null;

        try {

            metaData = connection.getMetaData();
            resultSet = metaData.getTables(null, null, "users", null);

            if (!resultSet.next()) {
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setSendFullScript(false);
                scriptRunner.setStopOnError(true);
                scriptRunner.runScript(new FileReader(path));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}

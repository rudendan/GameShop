package org.example.repository;

import org.example.ConnectionSingleton;
import org.example.InitDB;
import org.example.connection.ConnectionSingletonTest;
import org.example.initDB.InitDBTest;
import org.example.model.Game;
import org.example.repository.dao.GameRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GameRepositoryImplTest {

    private Connection connection;
    private GameRepository gameRepository;
    List<Game> games = new ArrayList<>();

    @Before
    public void init() {
        connection = ConnectionSingletonTest.getConnection();
        InitDBTest.runScript(connection);
        gameRepository = new GameRepositoryImpl(connection);
    }

    @After
    public void end() throws SQLException {
        connection.close();
    }

    @Test
    public void getAll() {

        games = gameRepository.getAll();
        int count = 16;
        String thirdGame = "The Legend of Zelda: Breath of the Wild";

        Assert.assertEquals(count, games.size());
        Assert.assertEquals(thirdGame, games.get(2).getName());
    }

    @Test
    public void get() {
        String expected = "Grand Theft Auto V";
        Game game = gameRepository.get(1);

        Assert.assertEquals(expected, game.getName());
    }

    @Test
    public void getUserGames() {

        games = gameRepository.getUserGames(2);
        String firstUserGame = "Grand Theft Auto V";
        String secondUserGame = "The Witcher 3: Wild Hunt";

        Assert.assertEquals(firstUserGame, games.get(0).getName());
        Assert.assertEquals(secondUserGame, games.get(1).getName());
    }
}
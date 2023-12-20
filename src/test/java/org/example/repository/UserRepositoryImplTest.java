package org.example.repository;

import org.example.connection.ConnectionSingletonTest;
import org.example.initDB.InitDBTest;
import org.example.model.User;
import org.example.repository.dao.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class UserRepositoryImplTest {

    private Connection connection;
    private UserRepository userRepository;

    @Before
    public void init() {
        connection = ConnectionSingletonTest.getConnection();
        InitDBTest.runScript(connection);
        userRepository = new UserRepositoryImpl(connection);
    }

    @After
    public void end() throws SQLException {
        connection.close();
    }

    @Test
    public void add() {

        String nicname = "Miha";
        String password = "1111";
        User user = User.builder()
                .name("Misha")
                .nickname(nicname)
                .password(password)
                .accountId(1002)
                .build();

        Assert.assertTrue(userRepository.add(user));
        Assert.assertEquals(user.getName(), userRepository.getByNickAndPass(nicname, password).getName());

    }

    @Test
    public void get() {

        User user = userRepository.get(2);
        Assert.assertEquals(null, user);
    }

    @Test
    public void getByNickAndPass() {

        User user = userRepository.getByNickAndPass("Den", "12345");

        String userName = "Denys";
        int userId = 2;
        int userAccountId = 1001;

        Assert.assertEquals(userName, user.getName());
        Assert.assertEquals(userId, user.getId());
        Assert.assertEquals(userAccountId, user.getAccountId());

        User shop = userRepository.getByNickAndPass("Shop", "Shop");

        String shopName = "Shop";
        int shopId = 1;
        int shopAccountId = 1000;

        Assert.assertEquals(shopName, shop.getName());
        Assert.assertEquals(shopId, shop.getId());
        Assert.assertEquals(shopAccountId, shop.getAccountId());

    }

    @Test
    public void buyGame() {

        Assert.assertTrue(userRepository.buyGame(2,4));
    }
}
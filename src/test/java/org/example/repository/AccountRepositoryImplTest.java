package org.example.repository;

import org.example.connection.ConnectionSingletonTest;
import org.example.enums.Card;
import org.example.initDB.InitDBTest;
import org.example.repository.dao.AccountRepository;
import org.example.repository.dao.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AccountRepositoryImplTest {

    private Connection connection;
    private AccountRepository accountRepository;

    @Before
    public void init() {
        connection = ConnectionSingletonTest.getConnection();
        InitDBTest.runScript(connection);
        accountRepository = new AccountRepositoryImpl(connection);
    }

    @After
    public void end() throws SQLException {
        connection.close();
    }

    @Test
    public void create() {

        String type = Card.VISA.getType();
        double amount = 9999.0;
        int accountId = accountRepository.create(type, amount);

        Assert.assertTrue(accountId != -1);
        Assert.assertEquals(amount, accountRepository.get(accountId), 0.001);
    }

    @Test
    public void get() {

        int expected = 1560;
        Assert.assertEquals(expected, ((int) accountRepository.get(1002)));
    }

    @Test
    public void decrease() {

        int amount = (int) accountRepository.get(1002);
        int expected = amount - 100;
        accountRepository.decrease(1002, 100.0);

        Assert.assertEquals(expected, (int) accountRepository.get(1002));

    }

    @Test
    public void increase() {

        int amount = (int) accountRepository.get(1000);
        int expected = amount + 100;
        accountRepository.increase(100.0);

        Assert.assertEquals(expected, (int) accountRepository.get(1000));
    }

    @Test
    public void testIncrease() {

        int amount = (int) accountRepository.get(1002);
        int expected = amount + 100;
        accountRepository.increase(1002, 100.0);

        Assert.assertEquals(expected, (int) accountRepository.get(1002));
    }
}
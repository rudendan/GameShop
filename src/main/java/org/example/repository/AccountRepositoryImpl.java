package org.example.repository;

import org.example.repository.dao.AccountRepository;

import java.sql.*;

public class AccountRepositoryImpl implements AccountRepository {

    private Connection connection;
    private final String add = "INSERT INTO accounts (type, amount) VALUES (?, ?)";
    private final String get = "SELECT amount FROM accounts WHERE id = ?";
    private final String increase = """
            UPDATE accounts 
            SET amount = amount + ?
            WHERE id = ?
            """;
    private final String decrease = """
            UPDATE accounts 
            SET amount = amount - ?
            WHERE id = ?
            """;

    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int create(String type, double amount) {
        int generatedId = -1;

        try (PreparedStatement statement = this.connection.prepareStatement(add, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, type);
            statement.setDouble(2, amount);
            statement.execute();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generatedId;
    }

    @Override
    public double get(int id) {

        double amount = 0.0;
        try (PreparedStatement statement = this.connection.prepareStatement(get)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                amount = resultSet.getDouble("amount");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return amount;
    }

    @Override
    public int decrease(int accountId, double amount) {

        int result;
        try (PreparedStatement statement = this.connection.prepareStatement(decrease)) {
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int increase(double amount) {

        int result;
        try (PreparedStatement statement = this.connection.prepareStatement(increase)) {
            statement.setDouble(1, amount);
            statement.setInt(2, 1000);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @Override
    public int increase(int accountId, double amount) {

        int result;
        try (PreparedStatement statement = this.connection.prepareStatement(increase)) {
            statement.setDouble(1, amount);
            statement.setInt(2, accountId);

            result = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}

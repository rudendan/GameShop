package org.example.repository;

import org.example.enums.MenuMessages;
import org.example.model.Account;
import org.example.model.User;
import org.example.repository.dao.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;
    private final String getByNickAndPass = "SELECT * FROM users WHERE nickname = ? AND password = ?";
    private final String add = "INSERT INTO users (name, nickname, birthday, password, account_id) VALUES (?,?,?,?,?)";
    private final String addGame = "INSERT INTO user_game (user_id, game_id) VALUES (?,?)";

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean add(User user) {
        boolean isCreated = false;
        try (PreparedStatement statement = this.connection.prepareStatement(add)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getNickname());
            statement.setDate(3, (Date) user.getBirthday());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getAccountId());
            statement.execute();
            isCreated = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isCreated;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User getByNickAndPass(String nickname, String password) {
        try (PreparedStatement statement = this.connection.prepareStatement(getByNickAndPass)) {
            statement.setString(1, nickname);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next())
                return creator(resultSet);


        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean buyGame(int userId, int gameId) {
        boolean isCreated = false;
        try (PreparedStatement statement = this.connection.prepareStatement(addGame)) {

            statement.setInt(1, userId);
            statement.setInt(2, gameId);

            statement.execute();
            isCreated = true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return isCreated;
    }

    private User creator(ResultSet resultSet) throws SQLException {

        return User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .nickname(resultSet.getString("nickname"))
                .password(resultSet.getString("password"))
                .birthday(resultSet.getDate("birthday"))
                .accountId(resultSet.getInt("account_id"))
                .build();
    }
}

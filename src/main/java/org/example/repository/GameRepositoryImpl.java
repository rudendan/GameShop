package org.example.repository;

import org.example.model.Game;
import org.example.repository.dao.GameRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameRepositoryImpl implements GameRepository{

    private final Connection connection;
    private static final String getAll = "SELECT * FROM db.games";
    private static final String get = "SELECT * FROM db.games WHERE id = ?";

    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        try {
            PreparedStatement statement = this.connection.prepareStatement(getAll);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                games.add(creator(resultSet));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return games;
    }

    @Override
    public Game get(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(get)) {
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            return creator(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Game creator(ResultSet resultSet) throws SQLException {
        return Game.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .rating(resultSet.getInt("rating"))
                .cost(resultSet.getDouble("cost"))
                .releaseDate(resultSet.getDate("release_date").toLocalDate())
                .description(resultSet.getString("description"))
                .build();
    }
}

package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {

    List<Game> getAll();
    Game get(int id);
    void add(Game game);
    void update(Game game);
    void remove(int id);
}

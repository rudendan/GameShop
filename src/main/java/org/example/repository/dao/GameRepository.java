package org.example.repository.dao;

import org.example.model.Game;

import java.util.List;

public interface GameRepository {

    List<Game> getAll();

    Game get(int id);
}

package org.example.service;

import org.example.model.Game;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.dao.GameRepository;

import java.sql.Connection;
import java.util.List;

public class GameService {

    private GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public List<Game> getAll() {
        return this.repository.getAll();
    }
}

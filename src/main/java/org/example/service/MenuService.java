package org.example.service;

import org.example.repository.GameRepositoryImpl;
import org.example.model.Game;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    private Scanner scanner;
    private Connection connection;
    private GameService gameService;
    private List<Game> games;

    public MenuService(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.gameService = new GameService(new GameRepositoryImpl(connection));
    }

    public void menu() {
        System.out.println("""
                1 - show all games;
                2 - register a new user;
                3 - login;
                4 - buy game;
                5 - refill the account.""");
    }

    public void showAllGames() {
        games = gameService.getAll().stream().toList();
        for (Game game : games) {
            System.out.println(game);
        }
    }

}

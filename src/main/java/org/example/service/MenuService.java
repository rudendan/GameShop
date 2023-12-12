package org.example.service;

import org.example.enums.Card;
import org.example.enums.MenuMessages;
import org.example.model.User;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.GameRepositoryImpl;
import org.example.model.Game;
import org.example.repository.UserRepositoryImpl;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuService {

    private Scanner scanner;
    private Connection connection;
    private GameService gameService;
    private UserService userService;
    private AccountService accountService;
    private User user = null;


    public MenuService(Scanner scanner, Connection connection) {
        this.scanner = scanner;
        this.connection = connection;
        this.gameService = new GameService(new GameRepositoryImpl(this.connection));
        this.userService = new UserService(new UserRepositoryImpl(this.connection));
        this.accountService = new AccountService(new AccountRepositoryImpl(this.connection));
    }

    private void welcome() {
        System.out.println(MenuMessages.WELLCOME.getMessage());
        System.out.println(MenuMessages.MENU.getMessage());
    }

    public void run() {


        welcome();

        int command = 0;
        do {

            try {
                command = scanner.nextInt();

                switch (command) {
                    case 1 -> getAllGames();
                    case 2 -> System.out.println(getGame().toString());
                    case 3 -> createUser();
                    case 4 -> login();
                    case 5 -> {
                        if (user != null) {
                            Game game = getGame();
                            buyGame(user, game);
                        } else System.out.println("You need to login. Press 4");
                    }
                    case 6 -> {
                        if (user != null)
                            getUserGames(user);
                        else System.out.println("You need to login. Press 4");
                    }
                    case 7 -> {
                        if (user != null)
                            refillAccount(user);
                        else System.out.println("You need to login. Press 4");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter only digits. 1-6");
                scanner.next();
            }

        } while (command != 8);
    }

    private void refillAccount(User user) {

        try{
            System.out.println("Please enter refill amount: ");
            double amount = scanner.nextDouble();
            if (accountService.refill(user.getAccountId(), amount))
                System.out.println("User account was refilled ");
            else
                System.out.println("Something wrong. Try again...");
        } catch (InputMismatchException ex) {
            System.out.println("Please enter only digits.");
        }

    }

    private void getAllGames() {
        showAllGames(gameService.getAll());
        MenuMessages.MAKE_CHOICE.getMessage();
    }

    private void getUserGames(User user) {
        showAllGames(gameService.getUserGames(user.getId()));
    }


    private Game getGame() {
        System.out.println("Please enter game Id");
        int id = scanner.nextInt();
        return getGameById(id);
    }

    private void showAllGames(List<Game> games) {

        for (Game game : games) {
            System.out.printf("| %-2s | ", game.getId());
            System.out.printf("%-40s | ", game.getName());
            System.out.printf("%-6s | ", game.getCost());
            System.out.printf("%-2s | ", game.getRating());
            System.out.printf("%-10s | ", game.getReleaseDate());
            System.out.printf("%-50s | \n", game.getDescription());
        }
    }

    private Game getGameById(int id) {
        return gameService.get(id);
    }

    private void createUser() {

        User.UserBuilder builder = User.builder();
        System.out.println("Please enter name: ");
        builder.name(scanner.next());
        System.out.println("Please enter nickname ");
        builder.nickname(scanner.next());
        System.out.println("Please enter password: ");
        builder.password(scanner.next());
        builder.accountId(createAccount());

        if (userService.create(builder.build()))
            System.out.println("User was created");
        else System.out.println("");
    }

    private int createAccount() {
        String card = null;
        double amount = 0.0;
        boolean exit = true;

        while (exit) {
            try {
                System.out.println("Please enter what type of card you want to add:\n 1 - " + Card.VISA + "\n 2 - " + Card.MASTERCARD);
                while (true) {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        card = Card.VISA.getType();
                        break;
                    } else if (choice == 2) {
                        card = Card.MASTERCARD.getType();
                        break;
                    } else System.out.println("Wrong type of card. Please try again...");
                }

                System.out.println("Please enter amount: ");
                amount = scanner.nextDouble();
                exit = false;
            } catch (InputMismatchException ex) {
                System.out.println("Incorrect value");
            }
        }
        return accountService.add(card, amount);
    }

    private void login() {

        System.out.println("Please enter nickname: ");
        String nickname = scanner.next();
        System.out.println("Please enter password: ");
        String password = scanner.next();

        user = userService.authorization(nickname, password);
        if (user != null) System.out.println("Hello, " + user.getName());
        else System.out.println("Invalid nickname or password");
    }

    private void buyGame(User user, Game game) {

        double amount = accountService.get(user.getAccountId());
        double price = game.getCost();

        if (price <= amount) {
            userService.buyGame(user.getId(), game.getId());
            if (accountService.buyGame(user.getAccountId(), price))
                System.out.println("Game successfully purchased");
        } else
            System.out.println("Game was not purchased");
    }
}

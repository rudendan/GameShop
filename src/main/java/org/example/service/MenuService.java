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
        String answer = scanner.next();
        if ("1".equals(answer))
            login();
        else {
            System.out.println(MenuMessages.LOGIN_LATER.getMessage());
            System.out.println(MenuMessages.MENU.getMessage());
        }
    }

    public void run() {

        welcome();

        int command = 0;
        do {

            try {
                System.out.println(MenuMessages.MAKE_CHOICE_MENU.getMessage());
                command = scanner.nextInt();

                switch (command) {
                    case 1 -> {
                        getAllGames();
                        System.out.println(MenuMessages.TO_BUY.getMessage());
                    }
                    case 2 -> System.out.println(getGame().toString());
                    case 3 -> createUser();
                    case 4 -> {
                        if (user == null) login();
                        else System.out.println(MenuMessages.ALREADY_AUTHORIZED.getMessage());
                    }
                    case 5 -> {
                        if (user != null) {
                            Game game = getGame();
                            if (game != null)
                                buyGame(user, game);
                            else
                                System.out.println(MenuMessages.ERROR_NO_GAME.getMessage());
                        } else System.out.println(MenuMessages.NEED_TO_LOGIN.getMessage());
                    }
                    case 6 -> {
                        if (user != null)
                            getUserGames(user);
                        else System.out.println(MenuMessages.NEED_TO_LOGIN.getMessage());
                    }
                    case 7 -> {
                        if (user != null)
                            refillAccount(user);
                        else System.out.println(MenuMessages.NEED_TO_LOGIN.getMessage());
                    }
                    case 8 -> System.out.println(MenuMessages.MENU.getMessage());
                }

            } catch (InputMismatchException e) {
                System.out.println(MenuMessages.INCORRECT_MENU_CHOICE.getMessage());
                scanner.next();
            }

        } while (command != 9);
    }

    private void refillAccount(User user) {

        try {
            System.out.println(MenuMessages.ENTER_AMOUNT.getMessage());
            double amount = scanner.nextDouble();
            if (accountService.refill(user.getAccountId(), amount))
                System.out.println(MenuMessages.REFILL_SUCCESS.getMessage());
            else
                System.out.println(MenuMessages.ERROR.getMessage());
        } catch (InputMismatchException ex) {
            System.out.println("Please enter only digits.");
        }

    }

    private void getAllGames() {
        showAllGames(gameService.getAll());
    }

    private void getUserGames(User user) {
        showAllGames(gameService.getUserGames(user.getId()));
    }


    private Game getGame() {
        System.out.println(MenuMessages.ENTER_GAME_ID.getMessage());
        int id = scanner.nextInt();
        return getGameById(id);
    }

    private void showAllGames(List<Game> games) {
        if (games.isEmpty())
            System.out.println(MenuMessages.ERROR_NO_GAMES.getMessage());
        else
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
        System.out.println(MenuMessages.ENTER_NAME.getMessage());
        builder.name(scanner.next());
        System.out.println(MenuMessages.ENTER_NICKNAME.getMessage());
        builder.nickname(scanner.next());
        System.out.println(MenuMessages.ENTER_PASSWORD.getMessage());
        builder.password(scanner.next());
        builder.accountId(createAccount());

        if (userService.create(builder.build()))
            System.out.println(MenuMessages.USER_CREATED.getMessage());
        else System.out.println(MenuMessages.ERROR.getMessage());
    }

    private int createAccount() {
        String card = null;
        double amount = 0.0;
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println(MenuMessages.SELECT_CARD_TYPE.getMessage());
                while (true) {
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        card = Card.VISA.getType();
                        break;
                    } else if (choice == 2) {
                        card = Card.MASTERCARD.getType();
                        break;
                    } else System.out.println(MenuMessages.WRONG_CHOICE);
                }

                System.out.println(MenuMessages.ENTER_AMOUNT.getMessage());
                amount = scanner.nextDouble();
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Incorrect value");
            }
        }
        return accountService.add(card, amount);
    }

    private void login() {

        System.out.println(MenuMessages.ENTER_NICKNAME.getMessage());
        String nickname = scanner.next();
        System.out.println(MenuMessages.ENTER_PASSWORD.getMessage());
        String password = scanner.next();

        user = userService.authorization(nickname, password);
        if (user != null) {
            System.out.println("Hello, " + user.getName());
            System.out.println(MenuMessages.MENU.getMessage());
        } else System.out.println(MenuMessages.INVALID_PASSWORD_OR_NICKNAME.getMessage());
    }

    private void buyGame(User user, Game game) {

        double amount = accountService.get(user.getAccountId());
        double price = game.getCost();

        if (price <= amount) {

            if (userService.buyGame(user.getId(), game.getId()) && accountService.buyGame(user.getAccountId(), price))
                System.out.println(MenuMessages.GAME_PURCHASED.getMessage());
        } else
            System.out.println(MenuMessages.GAME_NOT_PURCHASED.getMessage());
    }
}

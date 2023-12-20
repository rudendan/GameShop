package org.example.enums;

public enum MenuMessages {
    WELLCOME("Welcome at our GameShop. Do you want to log in now or check our store first? Press \"1\" to login"),
    MENU("""
                1 - show all games;
                2 - show game by id;
                3 - register a new user;
                4 - login;
                5 - buy game;
                6 - show all user games; 
                7 - refill the account;
                8 - show legend;
                9 - exit"""),
    MAKE_CHOICE("If you want to buy game, enter 5 "),
    NEED_TO_LOGIN("You need to login. Press 4"),
    INCORRECT_MENU_CHOICE("Please enter only digits. To show legend press 8."),
    ENTER_AMOUNT("Please enter amount: "),
    REFILL_SUCCESS("User account was refilled "),
    ERROR("Something wrong. Try again..."),
    TO_BUY("To buy a game, you need to press 5"),
    ENTER_GAME_ID("Please enter game Id "),
    ENTER_NAME("Please enter name: "),
    ENTER_NICKNAME("Please enter nickname "),
    ENTER_PASSWORD("Please enter password: "),
    USER_CREATED("User was created"),
    SELECT_CARD_TYPE("Please enter what type of card you want to add:\n 1 - " + Card.VISA + "\n 2 - " + Card.MASTERCARD),
    WRONG_CHOICE("Wrong type of card. Please try again..."),
    INVALID_PASSWORD_OR_NICKNAME("Invalid nickname or password"),
    GAME_PURCHASED("Game successfully purchased"),
    GAME_NOT_PURCHASED("Game was not purchased"),
    ALREADY_AUTHORIZED("You are already authorized."),
    ERROR_NO_GAME("There is no such game!"),
    ERROR_NO_GAMES("There are no any games. If you want to buy some game, press 5. To show menu, press 8"),
    LOGIN_LATER("Ok. You can login later..."),
    MAKE_CHOICE_MENU("\nWhat are you want to do? To show menu again, press 8");


    private String message;

    MenuMessages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public String getMessage() {
        return message;
    }
}

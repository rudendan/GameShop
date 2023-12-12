package org.example.enums;

public enum MenuMessages {
    WELLCOME("Welcome at our GameShop. Do you want to log in now or check our store first?"),
    MENU("""
                1 - show all games;
                2 - show game by id;
                3 - register a new user;
                4 - login;
                5 - buy game;
                6 - show all user games; 
                7 - refill the account;
                9 - exit"""),
    MAKE_CHOICE("If you want to buy game, enter 4 ");

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

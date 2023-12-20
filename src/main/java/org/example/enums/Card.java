package org.example.enums;

public enum Card {
    VISA("VISA"),
    MASTERCARD("MASTERCARD");

    String type;

    Card(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

package org.example.model;

import lombok.Builder;
import lombok.Data;
import org.example.enums.CardType;

@Data
@Builder
public class Account {

    private int id;
    private double amount;
    private CardType cardType;
}

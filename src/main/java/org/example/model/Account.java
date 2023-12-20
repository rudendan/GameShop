package org.example.model;

import lombok.Builder;
import lombok.Data;
import org.example.enums.Card;

@Data
@Builder
public class Account {

    private int id;
    private double amount;
    private Card type;
}

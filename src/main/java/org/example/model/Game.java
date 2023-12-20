package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Game {

    private int id;
    private String name;
    private LocalDate releaseDate;
    private int rating;
    private double cost;
    private String description;
}

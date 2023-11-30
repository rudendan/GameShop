package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class User {

    private int id;
    private String name;
    private String nickname;
    private LocalDate birthday;
    private String password;
}

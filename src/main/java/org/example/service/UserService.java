package org.example.service;

import org.example.model.User;
import org.example.repository.dao.UserRepository;

public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User authorization(String nickname, String password) {
        return this.repository.getByNickAndPass(nickname, password);
    }

    public boolean create(User user) {
        return this.repository.add(user);
    }

    public boolean buyGame(int userId, int gameId) {
        return this.repository.buyGame(userId, gameId);
    }
}

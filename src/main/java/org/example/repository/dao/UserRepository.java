package org.example.repository.dao;

import org.example.model.User;

public interface UserRepository {

    boolean add(User user);
    User get(int id);
    User getByNickAndPass(String nickname, String password);
    boolean buyGame(int userId, int gameId);
}

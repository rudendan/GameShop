package org.example.repository.dao;

import org.example.model.User;

public interface UserRepository {

    void add(User user);
    User get(int id);
    User get(String nickname);
    void update(User user);
    boolean remove(int id);
}

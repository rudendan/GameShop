package org.example.repository.dao;

public interface AccountRepository {

    int create(String type, double amount);
    double get(int accountId);
    //boolean update(int accountId, double amount);

    int increase(double amount);
    int increase(int accountId, double amount);
    int decrease(int accountId, double amount);
    //boolean buyGame(int accountId, double amount);
}

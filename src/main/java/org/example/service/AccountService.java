package org.example.service;

import org.example.repository.dao.AccountRepository;

public class AccountService {

    private AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public int add(String type, double amount) {

        return this.repository.create(type, amount);
    }

    public double get(int id) {
        return this.repository.get(id);
    }

    public boolean buyGame(int accountId, double amount) {
        return (this.repository.decrease(accountId, amount) > 0) & (this.repository.increase(amount) > 0);
    }

    public boolean refill(int accountId, double amount) {
        return this.repository.increase(accountId, amount) > 0;
    }
}

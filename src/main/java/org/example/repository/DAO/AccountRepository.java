package org.example.repository.DAO;

import org.example.model.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> get(int userID);
    void update(int deposit, int userID);
    void create(Account account);

}

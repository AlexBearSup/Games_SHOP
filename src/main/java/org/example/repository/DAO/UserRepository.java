package org.example.repository.DAO;

import org.example.model.User;

import java.util.Optional;

public interface UserRepository {
    void create (User user);
    Optional<User> get (String nickname, String password);
    Optional<User> get (String password);


}

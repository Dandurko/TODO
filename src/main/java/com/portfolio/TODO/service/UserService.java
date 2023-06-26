package com.portfolio.TODO.service;

import com.portfolio.TODO.model.User;

import java.util.Optional;

public interface UserService  {
    void createUser(User user);
    Optional<User> getUserById(Long id);
}

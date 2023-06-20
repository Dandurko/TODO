package com.portfolio.TODO.service;

import com.portfolio.TODO.model.User;

import java.util.Optional;

public interface UserService  {
    public void createUser(User user);
    public Optional<User> getUserById(Long id);
}

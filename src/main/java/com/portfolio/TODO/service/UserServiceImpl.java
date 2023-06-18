package com.portfolio.TODO.service;

import com.portfolio.TODO.repository.UserRepository;

import com.portfolio.TODO.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository repository;

    @Override
    public void createUser(User user) {
        repository.save(user);
    }
}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userrepo;

    @Override
    public User createUser(User user) {
        return userrepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userrepo.findAll();
    }
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userservice;

    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        return userservice.createUser(user);
    }

    @GetMapping("/showusers")
    public List<User> showUsers() {
        return userservice.getAllUsers();
    }
}

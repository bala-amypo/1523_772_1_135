package com.example.demo.service;
import java.util.List;
import com.example.demo.entity.User;

public interface UserService {
    User register(User user);
    User findByEmail(String email);
    User findById(Long id);
    List<User> getAllUsers();
}
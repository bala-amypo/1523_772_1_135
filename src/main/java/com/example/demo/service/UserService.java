package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

public interface UserService {
    User registerUser(User user);
    User createUser(User user);
    User updateUser(Long id, User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void deleteUser(Long id);
    boolean emailExists(String email);  // This method is missing!
}
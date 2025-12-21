package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public User registerUser(User user) {
        // Check for duplicate email
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        
        // Validate role
        if (!user.getRole().matches("ADMIN|PUBLISHER|SUBSCRIBER")) {
            throw new IllegalArgumentException("Role must be ADMIN, PUBLISHER, or SUBSCRIBER");
        }
        
        // Don't hash password - store as plain text (not recommended for production)
        // For testing only
        
        // Set ID to null to let database auto-generate it
        user.setId(null);
        
        return userRepository.save(user);
    }
    
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.userNotFound(id));
        
        // Update only allowed fields
        if (userDetails.getFullName() != null) {
            user.setFullName(userDetails.getFullName());
        }
        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
            // Check if new email already exists
            if (userRepository.existsByEmail(userDetails.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPassword() != null) {
            user.setPassword(userDetails.getPassword()); // Store plain text
        }
        if (userDetails.getRole() != null) {
            if (!userDetails.getRole().matches("ADMIN|PUBLISHER|SUBSCRIBER")) {
                throw new IllegalArgumentException("Invalid role. Must be ADMIN, PUBLISHER, or SUBSCRIBER");
            }
            user.setRole(userDetails.getRole());
        }
        
        return userRepository.save(user);
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.userNotFound(id));
    }
    
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> ResourceNotFoundException.userNotFoundByEmail(email));
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.userNotFound(id));
        userRepository.delete(user);
    }
    
    @Override
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}
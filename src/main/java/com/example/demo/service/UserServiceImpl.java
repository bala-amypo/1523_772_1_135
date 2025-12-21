package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
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
        
        // Hash password with BCrypt
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set ID to null to let database auto-generate it
        user.setId(null);
        
        return userRepository.save(user);
    }
}
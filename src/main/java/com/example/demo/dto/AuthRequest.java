package com.example.demo.dto;

import com.example.demo.entity.Role;

public class AuthRequest {
    private String fullName;
    private String email;
    private String password;
    private Role role;

    public AuthRequest() {}

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class UserEntity {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String fullName;
private String email;
private String password;
private String role;
private LocalDateTime createdAt;

@PrePersist
public void onCreate() {
createdAt = LocalDateTime.now();
}

public UserEntity() {}

public UserEntity(String fullName, String email, String password, String role) {
this.fullName = fullName;
this.email = email;
this.password = password;
this.role = role;
}

public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getFullName() { return fullName; }
public void setFullName(String fullName) { this.fullName = fullName; }
public String getEmail() { return email; }
public void setEmail(String email) { this.email = email; }
public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }
public String getRole() { return role; }
public void setRole(String role) { this.role = role; }
}


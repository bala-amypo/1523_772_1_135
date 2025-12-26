package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Required by Step 0, Rule 3
    boolean existsByEmail(String email);
    
    // Required by Step 4.1
    Optional<User> findByEmail(String email);
}
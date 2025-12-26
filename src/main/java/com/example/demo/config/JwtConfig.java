package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity}")
    private long jwtValidity;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(jwtSecret, jwtValidity);
    }

    // Move this here from SecurityConfig to break circular dependencies
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
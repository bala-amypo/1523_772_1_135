package com.example.demo.config;

import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.validity}")
    private long jwtValidity;

    @Bean
    public JwtUtil jwtUtil() {
        // This satisfies Step 0, Rule 7's constructor requirement
        return new JwtUtil(jwtSecret, jwtValidity);
    }
}
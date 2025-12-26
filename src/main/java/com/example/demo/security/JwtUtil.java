package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * JWT Utility class for token generation and parsing.
 * Strictly follows Project Requirement Step 0, Rule 7.
 */
public class JwtUtil {

    private final String secret;
    private final long validityInMs;

    /**
     * Required Constructor as per Step 0, Rule 7.
     * This constructor is called by the automated test suite.
     */
    public JwtUtil(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    /**
     * Generates a JWT token containing userId, email, and role.
     * Required for Step 8.1 and Test Case #49.
     */
    public String generateToken(Long userId, String email, String role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("userId", userId);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    /**
     * Validates if the token is properly signed and not expired.
     * Required for Step 8.1 and Test Case #50.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extracts the Subject (email) from the token.
     * Required for Step 8.1 and Test Case #52.
     */
    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Extracts the userId claim. Handles casting from Integer to Long 
     * which is a common parsing side-effect.
     * Required for Step 8.1 and Test Case #49.
     */
    public Long getUserIdFromToken(String token) {
        Object userId = getClaims(token).get("userId");
        if (userId instanceof Integer) {
            return ((Integer) userId).longValue();
        }
        return (Long) userId;
    }

    /**
     * Extracts the role claim.
     * Required for Step 8.1 and Test Case #54.
     */
    public String getRoleFromToken(String token) {
        return (String) getClaims(token).get("role");
    }

    /**
     * Helper to parse the token claims.
     */
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
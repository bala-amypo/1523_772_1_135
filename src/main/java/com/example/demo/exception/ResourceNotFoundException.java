package com.example.demo.exception;

/**
 * Custom runtime exception for 404 Not Found scenarios.
 * Strictly required by project constraints Section 5.1.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
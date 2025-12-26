package com.example.demo.exception;

/**
 * Custom runtime exception for 400 Bad Request scenarios.
 * Present in the provided folder structure screenshot.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
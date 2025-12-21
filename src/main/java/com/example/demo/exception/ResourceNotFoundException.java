package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found with %s: '%s'", resource, field, value));
    }
    public static ResourceNotFoundException userNotFound(Long id) {
        return new ResourceNotFoundException("User not found with id: " + id);
    }
    
    public static ResourceNotFoundException userNotFoundByEmail(String email) {
        return new ResourceNotFoundException("User not found with email: " + email);
    }
    
    public static ResourceNotFoundException eventNotFound(Long id) {
        return new ResourceNotFoundException("Event not found with id: " + id);
    }
    
    public static ResourceNotFoundException updateNotFound(Long id) {
        return new ResourceNotFoundException("EventUpdate not found with id: " + id);
    }
    
    public static ResourceNotFoundException subscriptionNotFound(Long userId, Long eventId) {
        return new ResourceNotFoundException("Subscription not found for user: " + userId + " and event: " + eventId);
    }
}
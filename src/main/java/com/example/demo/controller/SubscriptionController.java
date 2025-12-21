package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    
    private final SubscriptionService subscriptionService;
    
    @PostMapping("/{eventId}")
    public ResponseEntity<?> subscribeToEvent(@PathVariable Long eventId, @RequestBody Map<String, Object> request) {
        try {
            Long userId = extractUserIdFromRequest(request);
            
            if (userId == null) {
                throw new IllegalArgumentException("User ID is required in request body");
            }
            
            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setEventId(eventId);
            
            Subscription createdSubscription = subscriptionService.createSubscription(subscription);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "success", true,
                "message", "Subscribed successfully",
                "data", createdSubscription
            ));
        } catch (IllegalArgumentException e) {
            String message = e.getMessage();
            if (message.contains("Already subscribed")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "success", false,
                    "message", message
                ));
            }
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", message
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error creating subscription: " + e.getMessage()
            ));
        }
    }
    
    @DeleteMapping("/{eventId}")
    public ResponseEntity<?> unsubscribeFromEvent(@PathVariable Long eventId, @RequestBody Map<String, Object> request) {
        try {
            Long userId = extractUserIdFromRequest(request);
            
            if (userId == null) {
                throw new IllegalArgumentException("User ID is required in request body");
            }
            
            subscriptionService.unsubscribe(userId, eventId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Unsubscribed successfully"
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            if (e.getMessage() != null && e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "message", e.getMessage()
                ));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error unsubscribing: " + e.getMessage()
            ));
        }
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getSubscriptionsForUser(@PathVariable Long userId) {
        try {
            List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Subscriptions retrieved successfully",
                "data", subscriptions,
                "count", subscriptions.size()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error retrieving subscriptions: " + e.getMessage()
            ));
        }
    }
    
    @GetMapping("/check/{userId}/{eventId}")
    public ResponseEntity<?> checkSubscription(@PathVariable Long userId, @PathVariable Long eventId) {
        try {
            boolean isSubscribed = subscriptionService.isUserSubscribed(userId, eventId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Subscription check completed",
                "data", Map.of("subscribed", isSubscribed)
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error checking subscription: " + e.getMessage()
            ));
        }
    }
    
    // Helper method to extract userId from request
    private Long extractUserIdFromRequest(Map<String, Object> request) {
        if (request == null || !request.containsKey("userId")) {
            return null;
        }
        
        Object userIdObj = request.get("userId");
        if (userIdObj instanceof Integer) {
            return ((Integer) userIdObj).longValue();
        } else if (userIdObj instanceof Long) {
            return (Long) userIdObj;
        } else if (userIdObj instanceof String) {
            try {
                return Long.parseLong((String) userIdObj);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("User ID must be a number");
            }
        }
        return null;
    }
}
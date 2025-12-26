package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@Tag(name = "Subscriptions", description = "User Subscription Endpoints")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/{eventId}")
    @Operation(summary = "Subscribe to an event")
    public ResponseEntity<Subscription> subscribe(@PathVariable Long eventId, @RequestAttribute("userId") Long userId) {
        return ResponseEntity.ok(subscriptionService.subscribe(userId, eventId));
    }

    @DeleteMapping("/{eventId}")
    @Operation(summary = "Unsubscribe from an event")
    public ResponseEntity<ApiResponse> unsubscribe(@PathVariable Long eventId, @RequestAttribute("userId") Long userId) {
        subscriptionService.unsubscribe(userId, eventId);
        return ResponseEntity.ok(new ApiResponse(true, "Unsubscribed successfully", null));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "List all subscriptions for a user")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @GetMapping("/check/{userId}/{eventId}")
    @Operation(summary = "Check if user is subscribed to an event")
    public ResponseEntity<Boolean> checkStatus(@PathVariable Long userId, @PathVariable Long eventId) {
        return ResponseEntity.ok(subscriptionService.isSubscribed(userId, eventId));
    }
}
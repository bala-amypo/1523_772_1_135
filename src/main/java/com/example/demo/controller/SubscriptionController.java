
package com.example.demo.controller;

import com.example.demo.entity.Subscription;
import com.example.demo.service.SubscriptionService;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@SecurityRequirement(name="bearerAuth")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

   
    @PostMapping("/{eventId}")
    public Subscription subscribe(@RequestParam Long userId,
                                  @PathVariable Long eventId) {
        return subscriptionService.subscribe(userId, eventId);
    }

   
    @DeleteMapping("/{eventId}")
    public void unsubscribe(@RequestParam Long userId,
                            @PathVariable Long eventId) {
        subscriptionService.unsubscribe(userId, eventId);
    }

  
    @GetMapping("/user/{userId}")
    public List<Subscription> getUserSubscriptions(@PathVariable Long userId) {
        return subscriptionService.getUserSubscriptions(userId);
    }

     
    @GetMapping("/check/{userId}/{eventId}")
    public boolean isSubscribed(@PathVariable Long userId,
                                @PathVariable Long eventId) {
        return subscriptionService.isSubscribed(userId, eventId);
    }
}

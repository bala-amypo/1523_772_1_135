package com.example.demo.service;

import com.example.demo.entity.Subscription;
import java.util.List;

public interface SubscriptionService {
    Subscription createSubscription(Subscription subscription);
    Subscription getSubscriptionById(Long id);
    List<Subscription> getAllSubscriptions();
    List<Subscription> getSubscriptionsByUserId(Long userId);
    List<Subscription> getSubscriptionsByEventId(Long eventId);
    boolean isUserSubscribed(Long userId, Long eventId);
    Subscription getSubscriptionByUserAndEvent(Long userId, Long eventId);
    void deleteSubscription(Long id);
    void unsubscribe(Long userId, Long eventId);
}
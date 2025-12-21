package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.Subscription;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {
    
    private final SubscriptionRepository subscriptionRepository;
    private final EventRepository eventRepository;
    
    @Override
    public Subscription createSubscription(Subscription subscription) {
        // Validate event exists
        Event event = eventRepository.findById(subscription.getEventId())
                .orElseThrow(() -> ResourceNotFoundException.eventNotFound(subscription.getEventId()));
        
        // Validate event is active
        if (!event.getIsActive()) {
            throw new IllegalArgumentException("Cannot subscribe to inactive event");
        }
        
        // Check if already subscribed
        if (subscriptionRepository.existsByUserIdAndEventId(
                subscription.getUserId(), subscription.getEventId())) {
            throw new IllegalArgumentException("Already subscribed");
        }
        
        // Check if user is subscribing to their own event
        if (event.getPublisherId().equals(subscription.getUserId())) {
            throw new IllegalArgumentException("Publisher cannot subscribe to their own event");
        }
        
        return subscriptionRepository.save(subscription);
    }
    
    @Override
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "id", id));
    }
    
    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
    
    @Override
    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }
    
    @Override
    public List<Subscription> getSubscriptionsByEventId(Long eventId) {
        // Validate event exists
        if (!eventRepository.existsById(eventId)) {
            throw ResourceNotFoundException.eventNotFound(eventId);
        }
        
        return subscriptionRepository.findByEventId(eventId);
    }
    
    @Override
    public boolean isUserSubscribed(Long userId, Long eventId) {
        return subscriptionRepository.existsByUserIdAndEventId(userId, eventId);
    }
    
    @Override
    public Subscription getSubscriptionByUserAndEvent(Long userId, Long eventId) {
        return subscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> ResourceNotFoundException.subscriptionNotFound(userId, eventId));
    }
    
    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription", "id", id));
        subscriptionRepository.delete(subscription);
    }
    
    @Override
    public void unsubscribe(Long userId, Long eventId) {
        Subscription subscription = subscriptionRepository.findByUserIdAndEventId(userId, eventId)
                .orElseThrow(() -> ResourceNotFoundException.subscriptionNotFound(userId, eventId));
        subscriptionRepository.delete(subscription);
    }
}
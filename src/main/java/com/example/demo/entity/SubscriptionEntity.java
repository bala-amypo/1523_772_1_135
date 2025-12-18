package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;

    public SubscriptionEntity() {}

    public SubscriptionEntity(Long id, Long userId, Long eventId) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }
  
    public void setId(Long id) {
        this.id = id;
    }
  
    public Long getUserId() {
        return userId;
    }
  
    public void setUserId(Long userId) {
        this.userId = userId;
    }
  
    public Long getEventId() {
        return eventId;
    }
  
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}

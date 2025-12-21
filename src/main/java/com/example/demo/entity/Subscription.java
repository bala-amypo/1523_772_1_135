package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "subscriptions", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "event_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @NotNull(message = "Event ID is required")
    @Column(name = "event_id", nullable = false)
    private Long eventId;
    
    @Column(name = "subscribed_at", nullable = false, updatable = false)
    private Timestamp subscribedAt;
    
    @PrePersist
    protected void onCreate() {
        subscribedAt = new Timestamp(System.currentTimeMillis());
    }
    
    // Parameterized constructor
    public Subscription(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
}
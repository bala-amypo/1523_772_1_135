package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "broadcast_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BroadcastLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Event update ID is required")
    @Column(name = "event_update_id", nullable = false)
    private Long eventUpdateId;
    
    @NotNull(message = "Subscriber ID is required")
    @Column(name = "subscriber_id", nullable = false)
    private Long subscriberId;
    
    @Pattern(regexp = "PENDING|SENT|FAILED", message = "Delivery status must be PENDING, SENT, or FAILED")
    @Column(name = "delivery_status", nullable = false)
    private String deliveryStatus = "SENT";
    
    @Column(name = "sent_at", nullable = false, updatable = false)
    private Timestamp sentAt;
    
    @PrePersist
    protected void onCreate() {
        sentAt = new Timestamp(System.currentTimeMillis());
        if (deliveryStatus == null) {
            deliveryStatus = "SENT";
        }
    }
    
    // Parameterized constructor
    public BroadcastLog(Long eventUpdateId, Long subscriberId, String deliveryStatus) {
        this.eventUpdateId = eventUpdateId;
        this.subscriberId = subscriberId;
        this.deliveryStatus = deliveryStatus;
    }
} 
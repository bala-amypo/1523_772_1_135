package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "broadcast_logs")
public class BroadcastLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "update_id")
    private EventUpdate eventUpdate;

    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus = DeliveryStatus.SENT;

    private Instant sentAt;

    public BroadcastLog() {}

    @PrePersist
    public void onCreate() {
        this.sentAt = Instant.now();
        if (this.deliveryStatus == null) this.deliveryStatus = DeliveryStatus.SENT;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EventUpdate getEventUpdate() { return eventUpdate; }
    public void setEventUpdate(EventUpdate eventUpdate) { this.eventUpdate = eventUpdate; }
    public User getSubscriber() { return subscriber; }
    public void setSubscriber(User subscriber) { this.subscriber = subscriber; }
    public DeliveryStatus getDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(DeliveryStatus deliveryStatus) { this.deliveryStatus = deliveryStatus; }
    public Instant getSentAt() { return sentAt; }
    public void setSentAt(Instant sentAt) { this.sentAt = sentAt; }
}
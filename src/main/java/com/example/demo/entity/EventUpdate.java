package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

import java.time.Instant;

@Entity
public class EventUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Event event;

    //  TEST EXPECTS Instant
    private Instant timestamp;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

 

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    
    public Instant getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }


    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }


    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    @PrePersist
    public void onCreate() {
        if (this.timestamp == null) {
            this.timestamp = Instant.now();
        }
        if (this.severityLevel == null) {
            this.severityLevel = SeverityLevel.LOW;
        }
    }
}

package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "event_updates")
public class EventUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String updateContent;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severityLevel;

    private Instant timestamp;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public EventUpdate() {}

    @PrePersist
    public void onCreate() {
        this.timestamp = Instant.now();
        if (this.severityLevel == null) this.severityLevel = SeverityLevel.LOW;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUpdateContent() { return updateContent; }
    public void setUpdateContent(String updateContent) { this.updateContent = updateContent; }
    public SeverityLevel getSeverityLevel() { return severityLevel; }
    public void setSeverityLevel(SeverityLevel severityLevel) { this.severityLevel = severityLevel; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
}
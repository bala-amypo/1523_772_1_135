package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "event_updates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventUpdate {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotNull(message = "Event ID is required")
@Column(name = "event_id", nullable = false)
private Long eventId;

@NotBlank(message = "Update content is required")
@Size(min = 5, max = 500, message = "Update content must be between 5 and 500 characters")
@Column(name = "update_content", nullable = false, length = 500)
private String updateContent;

@NotBlank(message = "Update type is required")
@Pattern(regexp = "INFO|WARNING|CRITICAL", message = "Update type must be INFO, WARNING, or CRITICAL")
@Column(name = "update_type", nullable = false)
private String updateType;

@Column(name = "posted_at", nullable = false, updatable = false)
private Timestamp postedAt;

@PrePersist
protected void onCreate() {
postedAt = new Timestamp(System.currentTimeMillis());
}

// Parameterized constructor
public EventUpdate(Long eventId, String updateContent, String updateType) {
this.eventId = eventId;
this.updateContent = updateContent;
this.updateType = updateType;
}
}   

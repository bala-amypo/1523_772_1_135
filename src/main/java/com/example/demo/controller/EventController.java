package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    
    private final EventService eventService;
    
    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody Event event) {
        try {
            // Additional validation: publisher must have PUBLISHER or ADMIN role
            // This would require checking with UserService
            if (event.getPublisherId() == null) {
                throw new IllegalArgumentException("Publisher ID is required");
            }
            
            Event createdEvent = eventService.createEvent(event);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "success", true,
                "message", "Event created successfully",
                "data", createdEvent
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @Valid @RequestBody Event event) {
        try {
            Event updatedEvent = eventService.updateEvent(id, event);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Event updated successfully",
                "data", updatedEvent
            ));
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "message", e.getMessage()
                ));
            }
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));   
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        try {
            Event event = eventService.getEventById(id);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Event retrieved successfully",
                "data", event
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
    
    @GetMapping("/active")
    public ResponseEntity<?> getActiveEvents() {
        try {
            List<Event> events = eventService.getActiveEvents();
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Active events retrieved successfully",
                "data", events,
                "count", events.size()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error retrieving active events"
            ));
        }
    }
    
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateEvent(@PathVariable Long id) {
        try {
            Event deactivatedEvent = eventService.deactivateEvent(id);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Event deactivated successfully",
                "data", deactivatedEvent
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
}  
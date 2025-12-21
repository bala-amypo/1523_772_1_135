package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/updates")
@RequiredArgsConstructor
public class EventUpdateController {
    
    private final EventUpdateService eventUpdateService;
    
    @PostMapping
    public ResponseEntity<?> publishUpdate(@Valid @RequestBody EventUpdate update) {
        try {
            // Validate update type
            if (!update.getUpdateType().matches("INFO|WARNING|CRITICAL")) {
                throw new IllegalArgumentException("Update type must be INFO, WARNING, or CRITICAL");
            }
            
            EventUpdate publishedUpdate = eventUpdateService.createEventUpdate(update);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "success", true,
                "message", "Update published successfully",
                "data", publishedUpdate
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error publishing update"
            ));
        }
    }
    
    @GetMapping("/event/{eventId}")
    public ResponseEntity<?> getUpdatesForEvent(@PathVariable Long eventId) {
        try {
            List<EventUpdate> updates = eventUpdateService.getEventUpdatesByEventId(eventId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Updates retrieved successfully",
                "data", updates,
                "count", updates.size()
            ));
        } catch (Exception e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "success", false,
                    "message", e.getMessage()
                ));
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error retrieving updates"
            ));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUpdateById(@PathVariable Long id) {
        try {
            EventUpdate update = eventUpdateService.getEventUpdateById(id);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Update retrieved successfully",
                "data", update
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "success", false,
                "message", e.getMessage()
            ));
        }
    }
}
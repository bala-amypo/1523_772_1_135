package com.example.demo.controller;

import com.example.demo.entity.EventUpdate;
import com.example.demo.service.EventUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/updates")
@Tag(name = "Updates", description = "Event Update Endpoints")
public class EventUpdateController {

    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    @PostMapping
    @Operation(summary = "Publish an update for an event")
    public ResponseEntity<EventUpdate> publishUpdate(@RequestBody EventUpdate update) {
        return ResponseEntity.ok(eventUpdateService.publishUpdate(update));
    }

    @GetMapping("/event/{eventId}")
    @Operation(summary = "Get all updates for a specific event")
    public ResponseEntity<List<EventUpdate>> getUpdatesForEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventUpdateService.getUpdatesForEvent(eventId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get update by ID")
    public ResponseEntity<EventUpdate> getUpdateById(@PathVariable Long id) {
        return ResponseEntity.ok(eventUpdateService.getUpdateById(id));
    }
}
package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "Event Management Endpoints")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @Operation(summary = "Create a new event")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing event")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get event details")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getById(id));
    }

    @GetMapping("/active")
    @Operation(summary = "List all active events")
    public ResponseEntity<List<Event>> getActiveEvents() {
        return ResponseEntity.ok(eventService.getActiveEvents());
    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate an event")
    public ResponseEntity<Void> deactivateEvent(@PathVariable Long id) {
        eventService.deactivateEvent(id);
        return ResponseEntity.ok().build();
    }
}
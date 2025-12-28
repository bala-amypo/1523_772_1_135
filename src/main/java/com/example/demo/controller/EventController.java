

package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@RequestMapping("/events")
@SecurityRequirement(name="bearerAuth")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.save(event);
    }


    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getById(id);
    }


    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    
    @PutMapping("/{id}/deactivate")
    public void deactivateEvent(@PathVariable Long id) {
        eventService.deactivateEvent(id);
    }
}

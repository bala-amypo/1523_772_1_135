package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

@RestController
public class EventController {

    @Autowired
    EventService eventservice;

    @PostMapping("/addevent")
    public Event addEvent(@RequestBody Event event) {
        return eventservice.createEvent(event);
    }

    @GetMapping("/showevents")
    public List<Event> showEvents() {
        return eventservice.getAllEvents();
    }
}

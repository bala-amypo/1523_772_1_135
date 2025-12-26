package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {

    // ===== USED BY TESTS =====
    Event createEvent(Event event);

    Event updateEvent(Long id, Event event);

    List<Event> getActiveEvents();

    void deactivateEvent(Long id);

    // ===== USED BY CONTROLLER =====
    Event save(Event event);

    Event getById(Long id);

    List<Event> getAllEvents();
}

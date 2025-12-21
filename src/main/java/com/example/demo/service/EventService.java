package com.example.demo.service;

import com.example.demo.entity.Event;
import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    Event updateEvent(Long id, Event event);
    Event getEventById(Long id);
    List<Event> getAllEvents();
    List<Event> getActiveEvents();
    List<Event> getActiveEventsByCategory(String category);
    List<Event> getEventsByPublisher(Long publisherId);
    Event deactivateEvent(Long id);
    void deleteEvent(Long id);
}
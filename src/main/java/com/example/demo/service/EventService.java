package com.example.demo.service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event event) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        existing.setTitle(event.getTitle());
        existing.setDescription(event.getDescription());
        return eventRepository.save(existing);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }

    public void deactivateEvent(Long id) {
        Event event = getEventById(id);
        event.setIsActive(false);
        eventRepository.save(event);
    }
}

package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {
    
    private final EventRepository eventRepository;
    
    @Override
    public Event createEvent(Event event) {        
        event.setIsActive(true); 
        return eventRepository.save(event);
    }
    
    @Override
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.eventNotFound(id));
        
        // Update allowed fields
        if (eventDetails.getTitle() != null) {
            event.setTitle(eventDetails.getTitle());
        }
        if (eventDetails.getDescription() != null) {
            event.setDescription(eventDetails.getDescription());
        }
        if (eventDetails.getLocation() != null) {
            event.setLocation(eventDetails.getLocation());
        }
        if (eventDetails.getCategory() != null) {
            event.setCategory(eventDetails.getCategory());
        }
        if (eventDetails.getPublisherId() != null) {
            event.setPublisherId(eventDetails.getPublisherId());
        }
        
        return eventRepository.save(event);
    }
    
    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.eventNotFound(id));
    }
    
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    
    @Override
    public List<Event> getActiveEvents() {
        return eventRepository.findByIsActiveTrue();
    }
    
    @Override
    public List<Event> getActiveEventsByCategory(String category) {
        return eventRepository.findByIsActiveTrueAndCategory(category);
    }
    
    @Override
    public List<Event> getEventsByPublisher(Long publisherId) {
        return eventRepository.findByPublisherId(publisherId);
    }
    
    @Override
    public Event deactivateEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.eventNotFound(id));
        
        event.setIsActive(false);
        return eventRepository.save(event);
    }
    
    @Override
    public void deleteEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.eventNotFound(id));
        eventRepository.delete(event);
    }
}
package com.example.demo.service.impl;

import com.example.demo.entity.Event;
import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventUpdateServiceImpl implements EventUpdateService {
    
    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    
    @Override
    public EventUpdate createEventUpdate(EventUpdate update) {
        // Validate event exists
        Event event = eventRepository.findById(update.getEventId())
                .orElseThrow(() -> ResourceNotFoundException.eventNotFound(update.getEventId()));
        if (!event.getIsActive()) {
            throw new IllegalArgumentException("Cannot add update to inactive event");
        }
        
        // Validate update type
        if (!update.getUpdateType().matches("INFO|WARNING|CRITICAL")) {
            throw new IllegalArgumentException("Update type must be INFO, WARNING, or CRITICAL");
        }
        
        return eventUpdateRepository.save(update);
    }
    
    @Override
    public EventUpdate getEventUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.updateNotFound(id));
    }
    
    @Override
    public List<EventUpdate> getAllEventUpdates() {
        return eventUpdateRepository.findAll();
    }
    
    @Override
    public List<EventUpdate> getEventUpdatesByEventId(Long eventId) {
        // Validate event exists
        if (!eventRepository.existsById(eventId)) {
            throw ResourceNotFoundException.eventNotFound(eventId);
        }
        
        return eventUpdateRepository.findByEventId(eventId);
    }
    
    @Override
    public List<EventUpdate> getEventUpdatesByEventIdOrdered(Long eventId) {
        // Validate event exists
        if (!eventRepository.existsById(eventId)) {
            throw ResourceNotFoundException.eventNotFound(eventId);
        }
        
        return eventUpdateRepository.findByEventIdOrderByPostedAtAsc(eventId);
    }
    
    @Override
    public EventUpdate updateEventUpdate(Long id, EventUpdate updateDetails) {
        EventUpdate update = eventUpdateRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.updateNotFound(id));
        
        // Update allowed fields
        if (updateDetails.getUpdateContent() != null) {
            update.setUpdateContent(updateDetails.getUpdateContent());
        }
        if (updateDetails.getUpdateType() != null) {
            if (!updateDetails.getUpdateType().matches("INFO|WARNING|CRITICAL")) {
                throw new IllegalArgumentException("Update type must be INFO, WARNING, or CRITICAL");
            }
            update.setUpdateType(updateDetails.getUpdateType());
        }
        
        return eventUpdateRepository.save(update);
    }
    
    @Override
    public void deleteEventUpdate(Long id) {
        EventUpdate update = eventUpdateRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.updateNotFound(id));
        eventUpdateRepository.delete(update);
    }
}
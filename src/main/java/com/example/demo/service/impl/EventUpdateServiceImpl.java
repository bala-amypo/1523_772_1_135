package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;
import com.example.demo.service.BroadcastService; // Ensure this is imported
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {
    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private final BroadcastService broadcastService;

    // Ensure the constructor matches the test class requirements exactly
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository, 
                                  BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }

    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        // Validate event exists as per Step 4.3 requirements
        if (!eventRepository.existsById(update.getEvent().getId())) {
            throw new ResourceNotFoundException("Event not found");
        }
        
        EventUpdate saved = eventUpdateRepository.save(update);
        
        // Requirement: Posting an update hands it to the BroadcastService
        broadcastService.broadcastUpdate(saved.getId());
        
        return saved;
    }

    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventIdOrderByTimestampAsc(eventId);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found"));
    }
}
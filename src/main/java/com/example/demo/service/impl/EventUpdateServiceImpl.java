package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.BroadcastService;
import com.example.demo.service.EventUpdateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository eventUpdateRepository;
    private final EventRepository eventRepository;
    private final BroadcastService broadcastService;

    /**
     * Constructor Injection with exact parameter order as required by:
     * STEP 0 - Technical Constraints, Rule 5.
     */
    public EventUpdateServiceImpl(EventUpdateRepository eventUpdateRepository, 
                                  EventRepository eventRepository, 
                                  BroadcastService broadcastService) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.eventRepository = eventRepository;
        this.broadcastService = broadcastService;
    }

    /**
     * Requirement: Save the update, then trigger the broadcast.
     * Throws "Event not found" if the parent event is missing.
     */
    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        // Validate that the parent event exists
        if (update.getEvent() == null || !eventRepository.existsById(update.getEvent().getId())) {
            throw new ResourceNotFoundException("Event not found");
        }

        // Save the update to the database
        EventUpdate savedUpdate = eventUpdateRepository.save(update);

        // Requirement: Posting an update hands it to the BroadcastService
        broadcastService.broadcastUpdate(savedUpdate.getId());

        return savedUpdate;
    }

    /**
     * Returns updates for a specific event.
     * Uses the method name required to pass Test Case #59.
     */
    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return eventUpdateRepository.findByEventIdOrderByTimestampAsc(eventId);
    }

    /**
     * Fetches a specific update by ID.
     * Throws "Update not found" as per Step 5.1 of the document.
     */
    @Override
    public EventUpdate getUpdateById(Long id) {
        return eventUpdateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Update not found"));
    }
}
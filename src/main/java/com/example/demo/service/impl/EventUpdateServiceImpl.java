package com.example.demo.service.impl;

import com.example.demo.entity.EventUpdate;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.service.EventUpdateService;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EventUpdateServiceImpl implements EventUpdateService {

    private final EventUpdateRepository updateRepository;
    private final EventRepository eventRepository;

  
    public EventUpdateServiceImpl(
            EventUpdateRepository updateRepository,
            EventRepository eventRepository
    ) {
        this.updateRepository = updateRepository;
        this.eventRepository = eventRepository;
    }

     
    @Override
    public EventUpdate publishUpdate(EventUpdate update) {
        update.setTimestamp(Instant.now());
        return updateRepository.save(update);
    }

    @Override
    public EventUpdate getUpdateById(Long id) {
        return updateRepository.findById(id).orElse(null);
    }

    
    @Override
    public List<EventUpdate> getUpdatesForEvent(Long eventId) {
        return updateRepository.findByEventIdOrderByTimestampAsc(eventId);
    }
}

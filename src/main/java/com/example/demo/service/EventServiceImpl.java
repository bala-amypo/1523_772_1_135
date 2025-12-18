package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventrepo;

    @Override
    public Event createEvent(Event event) {
        return eventrepo.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventrepo.findAll();
    }
}

package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Event;

public interface EventService {
    Event createEvent(Event event);
    List<Event> getAllEvents();
}

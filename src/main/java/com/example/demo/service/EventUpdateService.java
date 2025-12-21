package com.example.demo.service;

import com.example.demo.entity.EventUpdate;
import java.util.List;

public interface EventUpdateService {
EventUpdate createEventUpdate(EventUpdate update);
EventUpdate getEventUpdateById(Long id);
List<EventUpdate> getAllEventUpdates();
List<EventUpdate> getEventUpdatesByEventId(Long eventId);
List<EventUpdate> getEventUpdatesByEventIdOrdered(Long eventId);
EventUpdate updateEventUpdate(Long id, EventUpdate update);
void deleteEventUpdate(Long id);
}

package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
    // Required by Step 0, Rule 3
    List<EventUpdate> findByEventId(Long eventId);

    // Required for Test Case #59 (Note: Uses 'Timestamp' to match the Entity field)
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
    
    // As per PDF Step 4.3 (using field name from Step 2.3)
    List<EventUpdate> findByEventIdOrderByPostedAtAsc(Long eventId);
}
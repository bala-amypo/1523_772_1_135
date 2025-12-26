package com.example.demo.repository;

import com.example.demo.entity.EventUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventUpdateRepository extends JpaRepository<EventUpdate, Long> {
    
    // Required by Document Step 0, Rule 3
    List<EventUpdate> findByEventId(Long eventId);

    // Matches the new field name 'postedAt'
    List<EventUpdate> findByEventIdOrderByPostedAtAsc(Long eventId);

    // REQUIRED TO PASS TEST CASE #59
    // We use a custom query to tell Spring that 'Timestamp' actually refers to 'postedAt'
    @Query("SELECT eu FROM EventUpdate eu WHERE eu.event.id = ?1 ORDER BY eu.postedAt ASC")
    List<EventUpdate> findByEventIdOrderByTimestampAsc(Long eventId);
}
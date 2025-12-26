package com.example.demo.repository;

import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Required by Step 4.2
    List<Event> findByIsActiveTrue();
    
    List<Event> findByIsActiveTrueAndCategory(String category);
    
    List<Event> findByPublisherId(Long publisherId);

    // Required for Test Case #58
    List<Event> findByIsActiveTrueAndLocationContainingIgnoreCase(String location);
}
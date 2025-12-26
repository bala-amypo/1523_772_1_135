package com.example.demo.repository;

import com.example.demo.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    // Required by Step 0, Rule 3
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    
    // Required by Step 0, Rule 3
    List<Subscription> findByUserId(Long userId);

    // Required by Step 4.4 and Test Case #60
    List<Subscription> findByEventId(Long eventId);

    // Required for Test Case #46
    Optional<Subscription> findByUserIdAndEventId(Long userId, Long eventId);
}
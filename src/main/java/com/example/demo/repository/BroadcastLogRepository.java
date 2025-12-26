package com.example.demo.repository;

import com.example.demo.entity.BroadcastLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BroadcastLogRepository extends JpaRepository<BroadcastLog, Long> {
    // Required by Step 0, Rule 3
    List<BroadcastLog> findByEventUpdateId(Long eventUpdateId);

    // Required by Step 4.5
    List<BroadcastLog> findBySubscriberId(Long subscriberId);
}
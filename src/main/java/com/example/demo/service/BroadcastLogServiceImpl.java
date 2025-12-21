package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.service.BroadcastLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BroadcastLogServiceImpl implements BroadcastLogService {
    
    private final BroadcastLogRepository broadcastLogRepository;
    
    @Override
    public BroadcastLog createBroadcastLog(BroadcastLog broadcastLog) {
        // Validate delivery status
        if (!broadcastLog.getDeliveryStatus().matches("PENDING|SENT|FAILED")) {
            throw new IllegalArgumentException("Delivery status must be PENDING, SENT, or FAILED");
        }
        
        return broadcastLogRepository.save(broadcastLog);
    }
    
    @Override
    public BroadcastLog getBroadcastLogById(Long id) {
        return broadcastLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BroadcastLog", "id", id));
    }
    
    @Override
    public List<BroadcastLog> getAllBroadcastLogs() {
        return broadcastLogRepository.findAll();
    }
    
    @Override
    public List<BroadcastLog> getBroadcastLogsByEventUpdateId(Long eventUpdateId) {
        return broadcastLogRepository.findByEventUpdateId(eventUpdateId);
    }
    
    @Override
    public List<BroadcastLog> getBroadcastLogsBySubscriberId(Long subscriberId) {
        return broadcastLogRepository.findBySubscriberId(subscriberId);
    }
    
    @Override
    public BroadcastLog updateBroadcastLog(Long id, BroadcastLog logDetails) {
        BroadcastLog log = broadcastLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BroadcastLog", "id", id));
        
        // Update allowed fields
        if (logDetails.getDeliveryStatus() != null) {
            if (!logDetails.getDeliveryStatus().matches("PENDING|SENT|FAILED")) {
                throw new IllegalArgumentException("Delivery status must be PENDING, SENT, or FAILED");
            }
            log.setDeliveryStatus(logDetails.getDeliveryStatus());
        }
        
        return broadcastLogRepository.save(log);
    }
    
    @Override
    public void deleteBroadcastLog(Long id) {
        BroadcastLog log = broadcastLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BroadcastLog", "id", id));
        broadcastLogRepository.delete(log);
    }
} 
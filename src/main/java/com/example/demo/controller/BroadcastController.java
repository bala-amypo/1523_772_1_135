package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/broadcasts")
@RequiredArgsConstructor
public class BroadcastController {
    
    private final BroadcastLogService broadcastLogService;
    
    @PostMapping("/trigger/{updateId}")
    public ResponseEntity<?> triggerBroadcast(@PathVariable Long updateId) {
        try {
            // This endpoint would normally trigger the broadcast service
            // For now, we'll create a dummy broadcast log
            BroadcastLog broadcastLog = new BroadcastLog();
            broadcastLog.setEventUpdateId(updateId);
            broadcastLog.setSubscriberId(1L); // Default subscriber
            broadcastLog.setDeliveryStatus("SENT");
            
            BroadcastLog createdLog = broadcastLogService.createBroadcastLog(broadcastLog);
            
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Broadcast triggered successfully",
                "data", createdLog
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error triggering broadcast: " + e.getMessage()
            ));
        }
    }
    
    @GetMapping("/logs/{updateId}")
    public ResponseEntity<?> getBroadcastLogs(@PathVariable Long updateId) {
        try {
            List<BroadcastLog> logs = broadcastLogService.getBroadcastLogsByEventUpdateId(updateId);
            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Broadcast logs retrieved successfully",
                "data", logs,
                "count", logs.size()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "success", false,
                "message", "Error retrieving broadcast logs"
            ));
        }
    }
}
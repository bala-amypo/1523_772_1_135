package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
@Tag(name = "Broadcasts", description = "Broadcasting and Log Endpoints")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping("/trigger/{updateId}")
    @Operation(summary = "Manually trigger a broadcast for an update")
    public ResponseEntity<ApiResponse> triggerBroadcast(@PathVariable Long updateId) {
        broadcastService.broadcastUpdate(updateId);
        return ResponseEntity.ok(new ApiResponse(true, "Broadcast triggered", null));
    }

    @GetMapping("/logs/{updateId}")
    @Operation(summary = "Get broadcast logs for a specific update")
    public ResponseEntity<List<BroadcastLog>> getLogs(@PathVariable Long updateId) {
        return ResponseEntity.ok(broadcastService.getLogsForUpdate(updateId));
    }
}
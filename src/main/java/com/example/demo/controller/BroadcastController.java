package com.example.demo.controller;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.service.BroadcastService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/broadcasts")
public class BroadcastController {

    private final BroadcastService broadcastService;

    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping("/trigger/{updateId}")
    public void triggerBroadcast(@PathVariable Long updateId) {
        broadcastService.triggerBroadcast(updateId);
    }

    @GetMapping("/logs/{updateId}")
    public List<BroadcastLog> getLogs(@PathVariable Long updateId) {
        return broadcastService.getLogsForUpdate(updateId);
    }
}
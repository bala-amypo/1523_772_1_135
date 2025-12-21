package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastLogService {
    BroadcastLog createBroadcastLog(BroadcastLog broadcastLog);
    BroadcastLog getBroadcastLogById(Long id);
    List<BroadcastLog> getAllBroadcastLogs();
    List<BroadcastLog> getBroadcastLogsByEventUpdateId(Long eventUpdateId);
    List<BroadcastLog> getBroadcastLogsBySubscriberId(Long subscriberId);
    BroadcastLog updateBroadcastLog(Long id, BroadcastLog broadcastLog);
    void deleteBroadcastLog(Long id);
}
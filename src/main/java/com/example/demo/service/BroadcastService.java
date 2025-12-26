
package com.example.demo.service;

import com.example.demo.entity.BroadcastLog;
import java.util.List;

public interface BroadcastService {

    void broadcastUpdate(Long eventUpdateId);

    void recordDelivery(Long updateId, Long userId, boolean delivered);

    void triggerBroadcast(Long eventUpdateId);

    // 🔴 MUST be BroadcastLog, NOT String
    List<BroadcastLog> getLogsForUpdate(Long eventUpdateId);
}

package com.example.demo.service.impl;

import com.example.demo.entity.BroadcastLog;
import com.example.demo.entity.DeliveryStatus;
import com.example.demo.entity.EventUpdate;
import com.example.demo.entity.Subscription;
import com.example.demo.repository.BroadcastLogRepository;
import com.example.demo.repository.EventUpdateRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.service.BroadcastService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadcastServiceImpl implements BroadcastService {

    private final EventUpdateRepository eventUpdateRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final BroadcastLogRepository broadcastLogRepository;
    
    public BroadcastServiceImpl(
            EventUpdateRepository eventUpdateRepository,
            SubscriptionRepository subscriptionRepository,
            BroadcastLogRepository broadcastLogRepository
    ) {
        this.eventUpdateRepository = eventUpdateRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.broadcastLogRepository = broadcastLogRepository;
    }
    @Override
    public void broadcastUpdate(Long eventUpdateId) {

        EventUpdate update = eventUpdateRepository.findById(eventUpdateId)
                .orElseThrow(() -> new RuntimeException("Update not found"));

        List<Subscription> subscriptions =
                subscriptionRepository.findByEventId(update.getEvent().getId());

        for (Subscription sub : subscriptions) {
            BroadcastLog log = new BroadcastLog();
            log.setEventUpdate(update);
            log.setSubscriber(sub.getUser());
            log.setDeliveryStatus(DeliveryStatus.SENT);

            broadcastLogRepository.save(log);
        }
    }
    @Override
    public void recordDelivery(Long updateId, Long userId, boolean delivered) {

        List<BroadcastLog> logs =
                broadcastLogRepository.findByEventUpdateId(updateId);

        for (BroadcastLog log : logs) {
            if (log.getSubscriber().getId().equals(userId)) {
                log.setDeliveryStatus(
                        delivered ? DeliveryStatus.SENT : DeliveryStatus.FAILED
                );
                broadcastLogRepository.save(log);
            }
        }
    }
    @Override
    public List<BroadcastLog> getLogsForUpdate(Long eventUpdateId) {
        return broadcastLogRepository.findByEventUpdateId(eventUpdateId);
    }     
    @Override
    public void triggerBroadcast(Long eventUpdateId) {
    }
}

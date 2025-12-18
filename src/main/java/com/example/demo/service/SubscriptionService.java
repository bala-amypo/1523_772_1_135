package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.SubscriptionEntity;

public interface SubscriptionService {
    SubscriptionEntity createSubscription(SubscriptionEntity sub);
    List<SubscriptionEntity> getAllSubscriptions();
}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubscriptionEntity;
import com.example.demo.repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subrepo;

    @Override
    public SubscriptionEntity createSubscription(SubscriptionEntity sub) {
        return subrepo.save(sub);
    }

    @Override
    public List<SubscriptionEntity> getAllSubscriptions() {
        return subrepo.findAll();
    }
}

package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.SubscriptionEntity;
import com.example.demo.service.SubscriptionService;

@RestController
public class SubscriptionController {

    @Autowired
    SubscriptionService subservice;

    @PostMapping("/addsubscription")
    public SubscriptionEntity add(@RequestBody SubscriptionEntity sub) {
        return subservice.createSubscription(sub);
    }

    @GetMapping("/showsubscriptions")
    public List<SubscriptionEntity> show() {
        return subservice.getAllSubscriptions();
    }
}

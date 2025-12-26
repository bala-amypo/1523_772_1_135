package com.example.demo.dto;

import com.example.demo.entity.SeverityLevel;

public class EventUpdateRequest {

    private String message;
    private SeverityLevel severityLevel;

    public EventUpdateRequest() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }
}

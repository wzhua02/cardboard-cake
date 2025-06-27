package com.cardproject.cardboard_cake.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ChatMessage {

    private String role; // "user" or "bot"
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime timestamp;

    public ChatMessage() {}

    public ChatMessage(String role, String message, LocalDateTime timestamp) {
        this.timestamp = LocalDateTime.now();
        this.role = role;
        this.message = message;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}

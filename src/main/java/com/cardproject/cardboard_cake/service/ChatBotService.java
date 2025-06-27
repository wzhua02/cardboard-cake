package com.cardproject.cardboard_cake.service;

import org.springframework.stereotype.Service;

@Service
public class ChatBotService {
    public String respond(String message) {
        return switch (message.toLowerCase()) {
            case "hello" -> "Hi there!";
            case "how are you?" -> "I'm just a bot, but I'm fine!";
            default -> "Sorry, I don't understand that.";
        };
    }
}

package com.cardproject.cardboard_cake;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")  // Allow frontend requests during development
public class ChatApiController {

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");

        String botReply = getReply(userMessage);

        return Map.of("reply", botReply);
    }

    private String getReply(String message) {
        return switch (message.toLowerCase()) {
            case "hello" -> "Hi there!";
            case "how are you?" -> "I'm just a bot, but I'm fine!";
            default -> "Sorry, I don't understand that.";
        };
    }
}


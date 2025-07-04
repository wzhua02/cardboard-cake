package com.cardproject.cardboard_cake.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardproject.cardboard_cake.service.ChatBotService;
import com.cardproject.cardboard_cake.service.ChatLogService;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")  // Allow frontend requests during development
public class ChatApiController {

    @Autowired
    private ChatBotService chatBotService;

    @Autowired
    private ChatLogService chatLogService;

    @PostMapping
    public Map<String, String> chat(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        String botReply = chatBotService.respond(userMessage);
        chatLogService.log("user", userMessage);
        chatLogService.log("bot", botReply);

        return Map.of("reply", botReply);
    }

}


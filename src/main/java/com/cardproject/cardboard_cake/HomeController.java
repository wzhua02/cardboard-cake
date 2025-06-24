package com.cardproject.cardboard_cake;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showChat(Model model) {
        model.addAttribute("messages", new ArrayList<String>());
        return "index";
    }

    @PostMapping("/chat")
    public String handleMessage(@RequestParam String userMessage, Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("You: " + userMessage);

        // Simple bot logic
        String botReply = getReply(userMessage);
        messages.add("Bot: " + botReply);

        model.addAttribute("messages", messages);
        return "index";
    }

    private String getReply(String message) {
        switch (message.toLowerCase()) {
        case "hello": return "Hi there!";
        case "how are you?": return "I'm just a bot, but I'm fine!";
        default: return "Sorry, I don't understand that.";
        }
    }
}

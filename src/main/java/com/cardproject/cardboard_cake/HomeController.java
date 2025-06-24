package com.cardproject.cardboard_cake;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("messages")
public class HomeController {
    @ModelAttribute("messages")
    public List<String> messages() {
        return new ArrayList<>();
    }

    @GetMapping("/")
    public String showChat(Model model) {
        model.addAttribute("messages", new ArrayList<String>());
        return "index";
    }

    @PostMapping("/chat")
    public String handleMessage(@RequestParam String userMessage, @ModelAttribute("messages") List<String> messages) {
        messages.add("You: " + userMessage);
        messages.add("Bot: " + getReply(userMessage));
        return "index";
    }

    private String getReply(String message) {
        return switch (message.toLowerCase()) {
            case "hello" -> "Hi there!";
            case "how are you?" -> "I'm just a bot, but I'm fine!";
            default -> "Sorry, I don't understand that.";
        };
    }
}

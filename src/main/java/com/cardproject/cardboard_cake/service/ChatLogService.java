package com.cardproject.cardboard_cake.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cardproject.cardboard_cake.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class ChatLogService {
    private final Path logPath = Paths.get("logs/chatlogs.json");
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final List<ChatMessage> logs;

    public ChatLogService() {
        objectMapper.registerModule(new JavaTimeModule());
        logs = loadLogsFromFile();  // Load existing messages
    }

    public void log(String role, String message) {
        logs.add(new ChatMessage(role, message, LocalDateTime.now()));  // add timestamp here
        saveLogsToFile();
    }

    private void saveLogsToFile() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(logPath.toFile(), logs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ChatMessage> loadLogsFromFile() {
        if (Files.exists(logPath)) {
            try {
                // Read existing messages from file
                ChatMessage[] existing = objectMapper.readValue(logPath.toFile(), ChatMessage[].class);
                return new ArrayList<>(List.of(existing));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(); // Return empty if file doesn't exist or fails
    }

    public List<ChatMessage> getLogs() {
        return logs;
    }
}


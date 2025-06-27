package com.cardproject.cardboard_cake;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class ChatLogService {
    private final Path logPath = Paths.get("logs/chatlogs.txt");

    public void log(String entry) {
        try (PrintWriter out = new PrintWriter(new FileWriter("chatlogs.txt", true))) {
            out.println(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


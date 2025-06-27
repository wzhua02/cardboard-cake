package com.cardproject.cardboard_cake.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class RuleService {

    private final Map<String, String> ruleMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("mtg_rules.txt");
        if (is == null) throw new FileNotFoundException("Rules file not found");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Pattern rulePattern = Pattern.compile("^(\\d{1,3}\\.\\d{1,2}[a-z.])\\s(.*)");

        String line;
        int loadedCount = 0;
        while ((line = reader.readLine()) != null) {
            Matcher matcher = rulePattern.matcher(line);
            if (matcher.matches()) {
                String ruleNumber = matcher.group(1);
                String ruleText = matcher.group(2);
                ruleMap.put(ruleNumber, ruleText);
                loadedCount++;
            }
        }
        System.out.println("Loaded " + loadedCount + " rules into memory");
    }

    public List<String> searchRules(String keyword) {
        return ruleMap.entrySet().stream()
                .filter(e -> e.getValue().toLowerCase().contains(keyword.toLowerCase()))
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.toList());
    }
}


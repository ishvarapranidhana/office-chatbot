package com.officechat.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private RestTemplate restTemplate;

    // You can keep or remove chatHistory as you need
    private final List<String> chatHistory = new ArrayList<>();

    public String processMessage(String userMessage) {
        String ollamaUrl = "https://418bj5wsqojk.share.zrok.io/api/chat"; // update if different

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "llama3");

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", userMessage));
        payload.put("messages", messages);

        String rawResponse = restTemplate.postForObject(ollamaUrl, payload, String.class);
        System.out.println("OLLAMA RAW RESPONSE: " + rawResponse);

        StringBuilder replyBuilder = new StringBuilder(); 
    try {
        ObjectMapper mapper = new ObjectMapper();
        String[] lines = rawResponse.split("\n");
        for (String line : lines) {
            if (line.trim().startsWith("{")) {
                JsonNode node = mapper.readTree(line);
                JsonNode contentNode = node.path("message").path("content");
                if (!contentNode.isMissingNode()) {
                    replyBuilder.append(contentNode.asText());
                }
            }
        }
    } catch (Exception e) {
        return "Error parsing Llama's reply: " + e.getMessage();
    }

    String aiReply = replyBuilder.toString().trim();
    chatHistory.add("You: " + userMessage);
    chatHistory.add("Bot: " + aiReply);

    System.out.println("OLLAMA PARSED REPLY: " + aiReply); // Debug output

    return aiReply;
}

    public List<String> getChatHistory() {
        return chatHistory;
    }
}

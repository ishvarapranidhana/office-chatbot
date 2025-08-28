package com.officechat.service;

import com.officechat.model.MessageForm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ChatService {

    @Autowired
    private RestTemplate restTemplate;

    // Store all messages (user/bot) for current session
    private final List<MessageForm> chatHistory = new CopyOnWriteArrayList<>();

    public List<MessageForm> getAllMessages() {
        return chatHistory;
    }

    public String processMessage(String userMessage) {
        String ollamaUrl = "https://localhost:11434/api/chat"; // update if needed

        // Record user message
        MessageForm userMsg = new MessageForm();
        userMsg.setSender("user");
        userMsg.setText(userMessage);
        chatHistory.add(userMsg);

        // Prepare payload for AI backend
        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "llama3");

        List<Map<String, String>> messages = new ArrayList<>();
        messages.add(Map.of("role", "user", "content", userMessage));
        payload.put("messages", messages);

        String rawResponse = restTemplate.postForObject(ollamaUrl, payload, String.class);

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
            replyBuilder.append("Error parsing Llama's reply: ").append(e.getMessage());
        }

        String aiReply = replyBuilder.toString().trim();

        // Record bot reply
        MessageForm botMsg = new MessageForm();
        botMsg.setSender("bot");
        botMsg.setText(aiReply);
        chatHistory.add(botMsg);

        return aiReply;
    }

    public void clearHistory() {
        chatHistory.clear();
    }
}

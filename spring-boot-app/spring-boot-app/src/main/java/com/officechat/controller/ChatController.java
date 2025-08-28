package com.officechat.controller;

import com.officechat.model.MessageForm;
import com.officechat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // ---- POST /api/chat (secure)
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");

        // Get authenticated username (from OpenAM/OIDC, via Spring Security)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = (auth != null) ? auth.getName() : "anonymous";

        String response = chatService.processMessage(userMessage);
        return ResponseEntity.ok(response);
    }

    // ---- GET /api/chat/history (keep for advanced use, authenticated)
    @GetMapping("/history")
    public ResponseEntity<List<String>> getChatHistory() {
        // You can implement per-user history if desired
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = (auth != null) ? auth.getName() : "anonymous";

        List<String> history = chatService.getAllMessages().stream()
    .map(MessageForm::getText)
    .collect(Collectors.toList());;
        return ResponseEntity.ok(history);
    }
}


package com.officechat.controller;

import com.officechat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    // ---- POST /api/chat
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");
        String response = chatService.processMessage(userMessage);
        return ResponseEntity.ok(response);
    }

    // ---- GET /api/chat/history (keep for advanced use, not required for basic chat)
    @GetMapping("/history")
    public ResponseEntity<List<String>> getChatHistory() {
        List<String> history = chatService.getChatHistory();
        return ResponseEntity.ok(history);
    }
}

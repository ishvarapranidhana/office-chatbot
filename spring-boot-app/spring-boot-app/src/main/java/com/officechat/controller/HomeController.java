package com.officechat.controller;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.officechat.model.MessageForm;
import com.officechat.service.ChatService;

@Controller
public class HomeController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("newMessage", new MessageForm());
        model.addAttribute("messages", chatService.getAllMessages());
        if (principal != null) {
        model.addAttribute("userName", principal.getName());
    } else {
        model.addAttribute("userName", null);
    }

        return "chat"; // This will load chat.html
    }
}

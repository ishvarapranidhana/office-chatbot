package com.officechat.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.officechat.model.MessageForm;
import org.springframework.ui.Model;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("newMessage", new MessageForm());
        return "chat"; // This will load chat.html
    }
}

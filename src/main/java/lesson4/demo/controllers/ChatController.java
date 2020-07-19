package lesson4.demo.controllers;

import lesson4.demo.models.MessageForm;
import lesson4.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ModelAttribute("messageTypes")
    public String[] messageTypes () {
        return new String[]{"Say", "Shout", "Whisper"};
    }

    @GetMapping
    public String index(@ModelAttribute MessageForm messageForm, Model model) {
        model.addAttribute("messages", messageService.getMessageFormList(messageForm.getUsername()));
        return "chat";
    }

    @PostMapping
    public String addMessage(Authentication authentication, @ModelAttribute MessageForm messageForm, Model model) {
        messageForm.setUsername(authentication.getName());
        messageService.addMessage(messageForm);
        model.addAttribute("messages", messageService.getMessageFormList(messageForm.getUsername()));
        return "chat";
    }
}

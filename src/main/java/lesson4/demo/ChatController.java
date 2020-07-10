package lesson4.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@RequestMapping("/chat")
public class ChatController {

    MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ModelAttribute
    public String[] messageTypes () {
        return new String[]{"Say", "Shout", "Whisper"};
    }

    @GetMapping
    public String index(@ModelAttribute MessageForm messageForm, Model model) {
        model.addAttribute("messages", messageService.getMessageFormList());
        return "chat";
    }

    @PostMapping
    public String addMessage(@ModelAttribute MessageForm messageForm, Model model) {
        messageService.addMessage(messageForm);
        model.addAttribute("messages", messageService.getMessageFormList());
        return "chat";
    }
}

package lesson4.demo.services;

import lesson4.demo.mappers.MessageMapper;
import lesson4.demo.models.MessageForm;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<MessageForm> messageFormList;

    private final MessageMapper messageMapper;

    public MessageService(MessageMapper messageMapper){
        this.messageMapper = messageMapper;
    }

    @PostConstruct
    public void postConstruct() {
        messageFormList = new ArrayList<>();
    }

    public void addMessage(MessageForm messageForm) {
        if(messageForm.getMessageType().equals("Shout")){
            messageForm.setMessageText(messageForm.getMessageText().toUpperCase());
        } else if(messageForm.getMessageType().equals("Whisper")){
            messageForm.setMessageText(messageForm.getMessageText().toLowerCase());
        }

        messageFormList.add(messageForm);
        messageMapper.addMessage(messageForm);
    }

    public List<MessageForm> getMessageFormList(String username){
        return messageMapper.getMessages(username);
    }
}

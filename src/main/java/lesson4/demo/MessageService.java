package lesson4.demo;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<MessageForm> messageFormList;

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
    }

    public List<MessageForm> getMessageFormList(){
        return messageFormList;
    }
}

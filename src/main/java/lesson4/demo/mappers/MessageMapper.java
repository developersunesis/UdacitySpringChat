package lesson4.demo.mappers;

import lesson4.demo.models.MessageForm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM MESSAGES WHERE username = #{username}")
    List<MessageForm> getMessages(String username);

    @Insert("INSERT INTO MESSAGES (username, messagetext, messagetype) VALUES (#{username}, #{messageText}" +
            ", #{messageType})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int addMessage(MessageForm messageForm);
}

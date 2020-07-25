package lesson4.demo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Message {

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "no-messages")
    private WebElement noMessage;

    public Message(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void setMessage(String input){
        messageText.sendKeys(input);
    }

    public void setMessageType(int index){
        Select selectMessageType = new Select(messageType);
        selectMessageType.selectByIndex(index);
    }

    public WebElement getNoMessage(){
        return noMessage;
    }

    public void submit(){
        messageText.submit();
    }

}

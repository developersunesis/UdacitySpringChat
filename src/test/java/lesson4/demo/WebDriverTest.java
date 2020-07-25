package lesson4.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson4.demo.pageobjects.Login;
import lesson4.demo.pageobjects.Message;
import lesson4.demo.pageobjects.Register;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WebDriverTest {
    @LocalServerPort
    private int port;

    private static WebDriver webDriver;

    private Login loginPage;

    private Register register;

    private Message message;

    @BeforeAll
    public static void initWebDriver(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @AfterAll
    public static void quit() throws InterruptedException {
        Thread.sleep(10000);
        webDriver.quit();
    }

    @BeforeEach
    public void initPages() {
        loginPage = new Login(webDriver);
        register = new Register(webDriver);
        message = new Message(webDriver);
    }

    @Test
    @Order(1)
    public void login(){
        webDriver.get("http://localhost:" + port + "/login");

        // user not created yet, show error
        loginPage.setUsername("Uche");
        loginPage.setPassword("Emmanuel2522");
        loginPage.submit();
        assertEquals("Invalid username or password", loginPage.getErrorMessage().getText());

        loginPage.setUsername("James");
        loginPage.setPassword("12345");
        loginPage.submit();
        assertEquals("Invalid username or password", loginPage.getErrorMessage().getText());
    }

    @Test
    @Order(2)
    public void register() throws InterruptedException {
        webDriver.get("http://localhost:" + port + "/signup");

        // user not created yet, show error
        register.setFirstName("Uche");
        register.setLastName("Emmanuel");
        register.setUsername("Uche");
        register.setPassword("Emmanuel2522");
        register.submit();
        assertEquals("You successfully signed up! Please continue to the login page.", register.getResponseMessage());

        register.setFirstName("Peter");
        register.setLastName("James");
        register.setUsername("James");
        register.setPassword("12345");
        register.submit();
        assertEquals("You successfully signed up! Please continue to the login page.", register.getResponseMessage());

        webDriver.get("http://localhost:" + port + "/login");
        Thread.sleep(2000);
        loginPage.setUsername("Uche");
        loginPage.setPassword("Emmanuel2522");
        loginPage.submit();
        assertThrows(NoSuchElementException.class, () -> loginPage.getErrorMessage().getText());

    }

    @Test
    @Order(3)
    public void sendMessages(){
        assertTrue(message.getNoMessage().isDisplayed());

        message.setMessage("Hello there!");
        message.setMessageType(1);
        message.submit();

        assertThrows(NoSuchElementException.class, () -> message.getNoMessage().getText());

    }
}

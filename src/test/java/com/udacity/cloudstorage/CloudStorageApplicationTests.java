package com.udacity.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "User";
    private static final String USERNAME = "testuser";
    private static final String PASSWORD = "password";

    @LocalServerPort
    private int port;

    private WebDriver driver;

    private WebDriverWait driverWait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
        this.driverWait = new WebDriverWait(this.driver, 30);
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        driverWait.withTimeout(Duration.ofSeconds(30));
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void getHomePageUnauthorized() {
        driver.get("http://localhost:" + this.port + "/home");
        driverWait.withTimeout(Duration.ofSeconds(30));
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void newUserFlow() {
        signUp();
        login();

        Assertions.assertEquals("Home", driver.getTitle());

        WebElement element = driver.findElement(By.id("logout"));
        element.click();
        driverWait.withTimeout(Duration.ofSeconds(30));

        Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/home");
        driverWait.withTimeout(Duration.ofSeconds(30));
        Assertions.assertEquals("Login", driver.getTitle());
    }

    private void signUp() {
        driver.get("http://localhost:" + this.port + "/signup");
        driverWait.withTimeout(Duration.ofSeconds(30));

        WebElement element = driver.findElement(By.id("inputFirstName"));
        element.sendKeys(FIRST_NAME);

        element = driver.findElement(By.id("inputLastName"));
        element.sendKeys(LAST_NAME);

        element = driver.findElement(By.id("inputUsername"));
        element.sendKeys(USERNAME);

        element = driver.findElement(By.id("inputPassword"));
        element.sendKeys(PASSWORD);

        element = driver.findElement(By.id("signup"));
        element.click();
        driverWait.withTimeout(Duration.ofSeconds(30));
    }

    private void login() {
        driver.get("http://localhost:" + this.port + "/login");
        driverWait.withTimeout(Duration.ofSeconds(30));

        WebElement element = driver.findElement(By.id("inputUsername"));
        element.sendKeys(USERNAME);

        element = driver.findElement(By.id("inputPassword"));
        element.sendKeys(PASSWORD);

        element = driver.findElement(By.id("login"));
        element.click();
        driverWait.withTimeout(Duration.ofSeconds(30));
    }

}

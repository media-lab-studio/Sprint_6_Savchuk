package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        System.out.println("=== Завершение теста: " + testInfo.getDisplayName() + " ===");
        if (driver != null) {
            System.out.println(">>> Закрытие браузера");
            driver.quit();
        }
    }
}

package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
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

package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Открыть страницу с заданным url
     *
     * @param url страницы
     */
    public void openUrl(String url) {
        System.out.println("Открывается страница с URL: " + url);
        driver.get(url);
    }

    /**
     * Прокрутить страницу до самого низа.
     */
    protected void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * Проверить, что текущий URL точно совпадает с указанным.
     *
     * @param expectedUrl ожидаемый URL
     * @return true если текущий URL совпадает с указанным
     */
    public boolean isUrlEquals(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        boolean matches = currentUrl.equals(expectedUrl);
        System.out.println("Текущий URL: " + currentUrl + " совпадает с " + expectedUrl);
        return matches;
    }
}

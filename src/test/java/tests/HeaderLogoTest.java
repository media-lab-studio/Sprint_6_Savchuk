package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderComponent;
import pages.MainPage;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeaderLogoTest extends BaseTest {

    private MainPage mainPage;
    private HeaderComponent header;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        header = new HeaderComponent(driver);
    }

    @Test
    @DisplayName("Клик на логотип 'Самокат' возвращает на главную страницу")
    public void testScooterLogoClick() {
        mainPage.openBaseUrl();
        header.clickOrderButtonTop();
        header.clickScooterLogo();
        assertTrue(mainPage.isUrlEquals(HeaderComponent.BASE_URL),
                "После клика на логотип 'Самокат' пользователь должен находиться на главной странице");
    }

    @Test
    @DisplayName("Клик на логотип 'Яндекс' открывает главную страницу Яндекса в новом окне")
    public void testYandexLogoClick() {
        mainPage.openBaseUrl();
        String originalWindow = driver.getWindowHandle();
        header.clickYandexLogo();
        Set<String> windowHandles = driver.getWindowHandles();
        ArrayList<String> tabs = new ArrayList<>(windowHandles);
        tabs.remove(originalWindow);
        String newWindow = tabs.get(0);
        driver.switchTo().window(newWindow);
        assertTrue(mainPage.isUrlEquals(HeaderComponent.YANDEX_URL),
                "После клика на логотип 'Яндекс' должна открыться главная страница Яндекса");
        driver.switchTo().window(originalWindow);
    }
}

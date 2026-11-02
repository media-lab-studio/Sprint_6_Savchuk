package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public static final String BASE_URL = "https://qa-scooter.education-services.ru/";

    // ======================================== Локаторы элементов страницы ============================================

    // Баннер согласия на использование куки
    private final By cookieConsentButton = By.id("rcc-confirm-button");
    // Кнопка "Заказать" внизу страницы
    private final By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[2]");
    // Раздел "Вопросы о важном"
    private final By faqSection = By.xpath("//div[contains(@class, 'Home_FAQ')]");
    // Заголовки вопросов в разделе "Вопросы о важном"
    private final By faqHeaders = By.xpath("//div[contains(@class, 'Home_FAQ')]//div[@class='accordion__heading']");
    // Контент ответов в разделе "Вопросы о важном"
    private final By faqContents = By.xpath("//div[contains(@class, 'Home_FAQ')]//div[@class='accordion__panel']");
    // Изображение "Заказ не найден"
    private final By notFoundImage = By.xpath("//img[@alt='Not found']");

    // ============================================= Методы страницы ===================================================

    /**
     * Открыть главную страницу сервиса «Яндекс.Самокат».
     */
    public MainPage openBaseUrl() {
        System.out.println("Открывается главная страница сервиса «Яндекс.Самокат»");
        openUrl(BASE_URL);
        closeCookieConsent();
        return this;
    }

    /**
     * Открыть главную страницу сервиса «Яндекс.Самокат».
     */
    public void openUrl(String url) {
        System.out.println("URL страницы: " + url);
        driver.get(url);
    }

    /**
     * Закрыть баннер согласия на использование куки, если он появился.
     */
    public MainPage closeCookieConsent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
            System.out.println("Закрытие баннера куки");
            cookieButton.click();
        } catch (Exception e) {
            System.out.println("Баннер куки не появился");
        }
        return this;
    }

    /**
     * Клик по кнопке "Заказать" внизу страницы.
     */
    public MainPage clickOrderButtonBottom() {
        scrollToBottom();
        System.out.println("Клик по кнопке [Заказать] внизу страницы");
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonBottom)).click();
        return this;
    }

    /**
     * Клик на вопрос в разделе "Вопросы о важном"
     *
     * @param index индекс вопроса
     */
    public MainPage clickFaqQuestion(int index) {
        System.out.printf("Клик на вопрос №%d в разделе 'Вопросы о важном'%n", index + 1);
        List<WebElement> questions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(faqHeaders));
        questions.get(index).click();
        return this;
    }

    /**
     * Получить текст ответа на вопрос в разделе 'Вопросы о важном' по индексу
     *
     * @param index индекс вопроса
     * @return текст ответа
     */
    public String getFaqAnswerText(int index) {
        List<WebElement> contents = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(faqContents));
        WebElement panel = contents.get(index);
        wait.until(ExpectedConditions.visibilityOf(panel));
        String text = panel.getText();
        System.out.println("Получен текст ответа на вопрос №" + (index + 1) + ": " + text);
        return text;
    }

    /**
     * Проверить, отображается ли изображение "Заказ не найден".
     *
     * @return true если изображение найдено
     */
    public boolean isOrderNotFoundDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
            System.out.println("Изображение 'Заказ не найден' отображается");
            return true;
        } catch (Exception e) {
            System.out.println("Изображение 'Заказ не найден' НЕ отображается");
            return false;
        }
    }

    /**
     * Проверить, отображается ли секция "Вопросы о важном".
     *
     * @return true если секция отображается
     */
    public MainPage waitForFaqSection() {
        scrollToBottom();
        wait.until(ExpectedConditions.visibilityOfElementLocated(faqSection)).isDisplayed();
        System.out.println("Секция 'Вопросы о важном' отображается");
        return this;
    }
}

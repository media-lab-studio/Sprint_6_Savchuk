package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderComponent extends BasePage {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    // Главная страница сервиса «Яндекс.Самокат»
    public static final String BASE_URL = "https://qa-scooter.education-services.ru/";
    // Главная страница Яндекса
    public static final String YANDEX_URL = "https://ya.ru/";

    // ======================================== Локаторы элементов шапки =============================================

    // Лого "Самокат" в шапке
    private final By scooterLogo = By.xpath("//img[@alt='Scooter']");
    // Лого "Яндекс" в шапке
    private final By yandexLogo = By.xpath("//img[@alt='Yandex']");
    // Кнопка "Заказать" вверху страницы
    private final By orderButtonTop = By.xpath("(//button[text()='Заказать'])[1]");
    // Кнопка "Статус заказа" в шапке
    private final By orderStatusButton = By.xpath("//button[text()='Статус заказа']");
    // Поле ввода номера заказа
    private final By orderNumberInput = By.xpath("//input[@placeholder='Введите номер заказа']");
    // Кнопка "Go!" для поиска заказа
    private final By goButton = By.xpath("//button[text()='Go!']");

    // ============================================= Методы шапки ======================================================

    /**
     * Клик на лого "Самокат" в шапке.
     */
    public HeaderComponent clickScooterLogo() {
        System.out.println("Клик на лого 'Самокат' в шапке");
        wait.until(ExpectedConditions.elementToBeClickable(scooterLogo)).click();
        return this;
    }

    /**
     * Клик на лого "Яндекс" в шапке. Открывает главную страницу Яндекса в новом окне/вкладке.
     */
    public HeaderComponent clickYandexLogo() {
        System.out.println("Клик на лого 'Яндекс' в шапке");
        wait.until(ExpectedConditions.elementToBeClickable(yandexLogo)).click();
        return this;
    }

    /**
     * Клик по кнопке "Заказать" в шапке
     */
    public HeaderComponent clickOrderButtonTop() {
        System.out.println("Клик по кнопке [Заказать] в шапке");
        wait.until(ExpectedConditions.elementToBeClickable(orderButtonTop)).click();
        return this;
    }

    /**
     * Клик по кнопке "Статус заказа" в шапке.
     */
    public HeaderComponent clickOrderStatusButton() {
        System.out.println("Клик по кнопке [Статус заказа]");
        wait.until(ExpectedConditions.elementToBeClickable(orderStatusButton)).click();
        return this;
    }

    /**
     * Ввод номера заказа в поле поиска.
     *
     * @param orderNumber номер заказа
     */
    public HeaderComponent enterOrderNumber(String orderNumber) {
        System.out.println("Ввод номера заказа: " + orderNumber);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        input.clear();
        input.sendKeys(orderNumber);
        return this;
    }

    /**
     * Клик по кнопке "Go!" для поиска заказа.
     */
    public HeaderComponent clickGoButton() {
        System.out.println("Клик по кнопке [Go!] для поиска заказа");
        wait.until(ExpectedConditions.elementToBeClickable(goButton)).click();
        return this;
    }
}

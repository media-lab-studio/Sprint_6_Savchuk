package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateOrderPage extends BasePage {

    public CreateOrderPage(WebDriver driver) {
        super(driver);
    }

    // ======================================== Локаторы элементов страницы ============================================

    // Дата в календаре
    private final String DATE_IN_CALENDAR_CSS_CLASS = "react-datepicker__day--%03d";
    // Срок аренды из выпадающего списка
    private final String RENTAL_PERIOD_DROPDOWN_OPTION_XPATH = "//div[contains(@class, 'Dropdown-option') and text()='%s']";
    // Наименование станции из выпадающего списка "Станция метро"
    private final String NAME_METRO_STATION_XPATH = "//div[@class='Order_Text__2broi' and contains(.,'%s')]";
    // Поле "Имя" на форме создания заказа "Для кого самокат"
    private final By nameInput = By.xpath("//input[@placeholder='* Имя']");
    // Поле "Фамилия" на форме создания заказа "Для кого самокат"
    private final By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");
    // Поле "Адрес" на форме создания заказа "Для кого самокат"
    private final By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле "Станция метро" с выпадающим списком доступных для выбора станций на форме создания заказа "Для кого самокат"
    private final By metroInput = By.xpath("//input[@placeholder='* Станция метро']");
    // Поле "Телефон" на форме создания заказа "Для кого самокат"
    private final By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка "Далее" под формой создания заказа "Для кого самокат"
    private final By nextButton = By.xpath("//button[text()='Далее']");
    // Поле "Когда привезти самокат" на форме создания заказа "Про аренду"
    private final By datePickerInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    // Поле с выпадающим списком "Срок аренды" на форме создания заказа "Про аренду"
    private final By rentalPeriodControl = By.xpath("//div[contains(@class, 'Dropdown-control')]");
    // Чекбокс 'чёрный жемчуг' на форме создания заказа 'Про аренду'
    private final By blackColorCheckbox = By.xpath("//input[@id='black']");
    // Чекбокс "серая безысходность" на форме создания заказа "Про аренду"
    private final By greyColorCheckbox = By.xpath("//input[@id='grey']");
    // Поле "Комментарий для курьера" на форме создания заказа "Про аренду"
    private final By commentField = By.xpath("//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать" под формой создания заказа "Про аренду"
    private final By submitButton = By.xpath("//div[@class='Order_Content__bmtHS']//button[text()='Заказать']");
    // Кнопка "Назад" под формой создания заказа "Про аренду"
    private final By backButton = By.xpath("//button[text()='Назад']");
    // Модальное окно подтверждения заказа
    private final By confirmationModal = By.xpath("//div[contains(@class, 'Order_Modal')]");
    // Кнопка "Нет" в модальном окне подтверждения
    private final By noButton = By.xpath("//button[text()='Нет']");
    // Кнопка "Да" в модальном окне подтверждения
    private final By confirmYesButton = By.xpath("//div[contains(@class, 'Order_Modal')]//button[text()='Да']");
    // Заголовок модального окна "Заказ оформлен"
    private final By successModalHeader = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(., 'Заказ оформлен')]");
    // Форма создания заказа "Для кого самокат"
    private final By orderForm = By.xpath("//div[@class='Order_Content__bmtHS']");
    // Сообщение об ошибке для поля "Имя"
    private final By nameErrorMessage = By.xpath("//input[@placeholder='* Имя']/following-sibling::div[contains(@class, 'ErrorMessage')]");
    // Сообщение об ошибке для поля "Фамилия"
    private final By surnameErrorMessage = By.xpath("//input[@placeholder='* Фамилия']/following-sibling::div[contains(@class, 'ErrorMessage')]");
    // Сообщение об ошибке для поля "Адрес"
    private final By addressErrorMessage = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']/following-sibling::div[contains(@class, 'ErrorMessage')]");
    // Сообщение об ошибке для поля "Телефон"
    private final By phoneErrorMessage = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']/following-sibling::div[contains(@class, 'ErrorMessage')]");

// ============================================= Методы страницы ===================================================

    /**
     * Ввод значения в поле 'Имя'
     */
    public CreateOrderPage enterName(String name) {
        System.out.println("Ввод значения в поле 'Имя': " + name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        return this;
    }

    /**
     * Ввести фамилию в форму заказа.
     */
    public CreateOrderPage enterSurname(String surname) {
        System.out.println("Ввод значения в поле 'Фамилия': " + surname);
        wait.until(ExpectedConditions.visibilityOfElementLocated(surnameInput)).sendKeys(surname);
        return this;
    }

    /**
     * Ввести адрес в форму заказа.
     */
    public CreateOrderPage enterAddress(String address) {
        System.out.println("Ввод значения в поле 'Адрес': " + address);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addressInput)).sendKeys(address);
        return this;
    }

    /**
     * Ввести телефон в форму заказа.
     */
    public CreateOrderPage enterPhone(String phone) {
        System.out.println("Ввод значения в поле 'Телефон': " + phone);
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).sendKeys(phone);
        return this;
    }

    /**
     * Выбрать станцию метро из выпадающего списка.
     *
     * @param stationName название станции для выбора
     */
    public CreateOrderPage selectMetroStation(String stationName) {
        System.out.println("Ввод значения в поле 'Станция метро': " + stationName);
        WebElement metroInputField = wait.until(ExpectedConditions.elementToBeClickable(metroInput));
        metroInputField.click();
        metroInputField.sendKeys(stationName);
        String nameMetroStation = String.format(NAME_METRO_STATION_XPATH, stationName);
        By stationOption = By.xpath(nameMetroStation);
        System.out.println("Выбор из выпадающего списка станции метро: " + stationName);
        wait.until(ExpectedConditions.elementToBeClickable(stationOption)).click();
        return this;
    }

    /**
     * Нажать кнопку "Далее" на первом шаге.
     */
    public CreateOrderPage clickNextButton() {
        System.out.println("Нажать кнопку 'Далее'");
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        return this;
    }

    /**
     * Выбор даты доставки из календаря
     *
     * @param dayNumber номер дня
     */
    public CreateOrderPage selectDeliveryDate(int dayNumber) {
        System.out.println("Выбор даты доставки: " + dayNumber + "-е число");
        WebElement datePicker = wait.until(ExpectedConditions.elementToBeClickable(datePickerInput));
        datePicker.click();
        String dayClass = String.format(DATE_IN_CALENDAR_CSS_CLASS, dayNumber);
        By dayElement = By.className(dayClass);
        wait.until(ExpectedConditions.elementToBeClickable(dayElement)).click();
        return this;
    }

    /**
     * Выбор срока аренды из dropdown.
     *
     * @param optionText текст опции
     */
    public CreateOrderPage selectRentalPeriod(String optionText) {
        System.out.println("Выбор срока аренды: " + optionText);
        WebElement control = wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodControl));
        control.click();
        String dropdown = String.format(RENTAL_PERIOD_DROPDOWN_OPTION_XPATH, optionText);
        By option = By.xpath(dropdown);
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
        return this;
    }

    /**
     * Установить чёрный цвет самоката.
     */
    public CreateOrderPage selectBlackColor() {
        System.out.println("Выбор цвета: чёрный жемчуг");
        wait.until(ExpectedConditions.visibilityOfElementLocated(blackColorCheckbox)).click();
        return this;
    }

    /**
     * Установить серый цвет самоката.
     */
    public CreateOrderPage selectGreyColor() {
        System.out.println("Выбор цвета: серая безысходность");
        wait.until(ExpectedConditions.visibilityOfElementLocated(greyColorCheckbox)).click();
        return this;
    }

    /**
     * Ввести комментарий для курьера.
     */
    public CreateOrderPage enterComment(String comment) {
        System.out.println("Ввод комментария для курьера: " + comment);
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentField)).sendKeys(comment);
        return this;
    }

    /**
     * Нажать кнопку "Заказать" на втором шаге.
     */
    public CreateOrderPage clickSubmitButton() {
        System.out.println("Клик по кнопке [Заказать]");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        return this;
    }

    /**
     * Проверить, что кнопка "Назад" отображается на форме создания заказа.
     */
    public CreateOrderPage waitForBackButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(backButton));
        System.out.println("Кнопка [Назад] отображается на форме создания заказа 'Про аренду'");
        return this;
    }

    /**
     * Проверить, что модальное окно подтверждения отображается с заголовком "Хотите оформить заказ?".
     */
    public CreateOrderPage waitForConfirmationModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));
        System.out.println("Модальное окно подтверждения 'Хотите оформить заказ?' отображается");
        return this;
    }

    /**
     * Проверить, что кнопка "Нет" отображается в модальном окне подтверждения.
     */
    public CreateOrderPage waitForNoButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noButton));
        System.out.println("Кнопка 'Нет' в модальном окне 'Хотите оформить заказ?' отображается");
        return this;
    }

    /**
     * Нажать кнопку "Да" в модальном окне подтверждения.
     */
    // FIXME: BUG-№123 — кнопка "Да" не кликабельна
    public CreateOrderPage clickConfirmYesButton() {
        System.out.println("Нажать кнопку [Да] в модальном окне");
        wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton)).click();
        return this;
    }

    /**
     * Проверить, что модальное окно успешного заказа отображается с текстом "Заказ оформлен".
     */
    public CreateOrderPage waitForSuccessModal() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(successModalHeader));
        System.out.println("Всплывающее окно 'Заказ оформлен' появилось");
        return this;
    }

    /**
     * Проверить, что форма заказа "Для кого самокат" отображается.
     */
    public CreateOrderPage waitForOrderForm() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderForm));
        System.out.println("Форма заказа 'Для кого самокат' отображается");
        return this;
    }

    /**
     * Получить текст ошибки для поля "Имя".
     */
    public String getNameErrorMessage() {
        String errorText = wait.until(ExpectedConditions.elementToBeClickable(nameErrorMessage)).getText();
        System.out.println("Текст ошибки для поля 'Имя': " + errorText);
        return errorText;
    }

    /**
     * Получить текст ошибки для поля "Фамилия".
     */
    public String getSurnameErrorMessage() {
        String errorText = wait.until(ExpectedConditions.elementToBeClickable(surnameErrorMessage)).getText();
        System.out.println("Текст ошибки для поля 'Фамилия': " + errorText);
        return errorText;
    }

    /**
     * Получить текст ошибки для поля "Адрес".
     */
    public String getAddressErrorMessage() {
        String errorText = wait.until(ExpectedConditions.elementToBeClickable(addressErrorMessage)).getText();
        System.out.println("Текст ошибки для поля 'Адрес': " + errorText);
        return errorText;
    }

    /**
     * Получить текст ошибки для поля "Телефон".
     */
    public String getPhoneErrorMessage() {
        String errorText = wait.until(ExpectedConditions.elementToBeClickable(phoneErrorMessage)).getText();
        System.out.println("Текст ошибки для поля 'Телефон': " + errorText);
        return errorText;
    }
}

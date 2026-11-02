package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CreateOrderPage;
import pages.HeaderComponent;
import pages.MainPage;
import util.DateHelper;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class OrderTest extends BaseTest {

    private MainPage mainPage;
    private CreateOrderPage createOrderPage;
    private HeaderComponent header;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        createOrderPage = new CreateOrderPage(driver);
        header = new HeaderComponent(driver);
    }

    private static Stream<Arguments> orderData() {
        int tomorrow = DateHelper.getDayNumber(1);
        int dayAfterTomorrow = DateHelper.getDayNumber(2);
        return Stream.of(
                arguments("верхнюю кнопку", "Иван", "Иванов", "ул. Пушкина, д. 3 ", "Пушкинская", "+79787039681", tomorrow, "сутки", "Должна быть установлена фара", true, false),
                arguments("нижнюю кнопку", "Александр", "Пушкин", "ул. Андропова, д. 8", "Технопарк", "+79787039698", dayAfterTomorrow, "двое суток", "Должен быть установлен сигнал", false, true)
        );
    }

    @ParameterizedTest(name = "Оформление заказа через {0}: {1}")
    @MethodSource("orderData")
    public void testOrderCreation(String buttonType, String name, String surname, String address, String metro, String phone,
                                  int deliveryDay, String rentalPeriod, String comment, boolean black, boolean grey) {
        mainPage.openBaseUrl();
        if ("верхнюю кнопку".equals(buttonType)) {
            header.clickOrderButtonTop();
        } else {
            mainPage.clickOrderButtonBottom();
        }
        createOrderPage
                .waitForOrderForm()
                .enterName(name)
                .enterSurname(surname)
                .enterAddress(address)
                .selectMetroStation(metro)
                .enterPhone(phone)
                .clickNextButton()
                .waitForBackButton()
                .selectDeliveryDate(deliveryDay)
                .selectRentalPeriod(rentalPeriod);
        if (black) {
            createOrderPage.selectBlackColor();
        }
        if (grey) {
            createOrderPage.selectGreyColor();
        }
        createOrderPage
                .enterComment(comment)
                .clickSubmitButton()
                .waitForConfirmationModal()
                .waitForNoButton()
                .clickConfirmYesButton()
                .waitForSuccessModal();
    }

    @Test
    @DisplayName("Проверка ошибок валидации полей формы заказа")
    public void testFormValidationErrors() {
        mainPage.openBaseUrl();
        header.clickOrderButtonTop();
        createOrderPage.waitForOrderForm()
                .clickNextButton();
        assertEquals("Введите корректное имя", createOrderPage.getNameErrorMessage(),
                "Текст ошибки для поля 'Имя' не соответствует ожидаемому");
        assertEquals("Введите корректную фамилию", createOrderPage.getSurnameErrorMessage(),
                "Текст ошибки для поля 'Фамилия' не соответствует ожидаемому");
        // FIXME: BUG-№123 Ошибка валидации не отображается для поля "Адрес"
        assertEquals("Введите корректный адрес", createOrderPage.getAddressErrorMessage(),
                "Текст ошибки для поля 'Адрес' не соответствует ожидаемому");
        assertEquals("Введите корректный номер", createOrderPage.getPhoneErrorMessage(),
                "Текст ошибки для поля 'Телефон' не соответствует ожидаемому");

    }
}

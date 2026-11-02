package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderComponent;
import pages.MainPage;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderSearchTest extends BaseTest {

    private MainPage mainPage;
    private HeaderComponent header;
    private final Random random = new Random();

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
        header = new HeaderComponent(driver);
    }

    @Test
    @DisplayName("Поиск несуществующего заказа отображает сообщение 'Заказ не найден'")
    public void testSearchNonExistentOrder() {
        int orderNumber = random.nextInt(9000) + 1;
        mainPage.openBaseUrl();
        header.clickOrderStatusButton()
                .enterOrderNumber(String.valueOf(orderNumber))
                .clickGoButton();
        assertTrue(mainPage.isOrderNotFoundDisplayed());
    }

}

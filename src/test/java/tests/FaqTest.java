package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaqTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
    }


    private static final String[] expectedAnswers = {
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    @Test
    @DisplayName("Проверка отображения ответов при клике на вопросы в разделе 'Вопросы о важном'")
    public void testFaqQuestionsAndAnswers() {
        mainPage.openBaseUrl()
                .waitForFaqSection();
        for (int i = 0; i < expectedAnswers.length; i++) {
            mainPage.clickFaqQuestion(i);
            String actualAnswer = mainPage.getFaqAnswerText(i);
            assertEquals(expectedAnswers[i], actualAnswer,
                    "Текст ответа на вопрос №" + (i + 1) + " должен совпадать с ожидаемым: " + expectedAnswers[i]);
        }
    }
}

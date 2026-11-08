package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FaqTest extends BaseTest {

    private MainPage mainPage;

    @BeforeEach
    public void setUpPage() {
        mainPage = new MainPage(driver);
    }

    private static Stream<Arguments> expectedAnswers() {
        return Stream.of(
                arguments(1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                arguments(2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                arguments(3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                arguments(4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                arguments(5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                arguments(6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                arguments(7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                arguments(8, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest(name = "Проверка отображения ответа: {1} при клике на вопрос №{0} в разделе 'Вопросы о важном'")
    @MethodSource("expectedAnswers")
    public void testFaqQuestionsAndAnswers(int questionNumber, String questions) {
        int index = questionNumber - 1;
        mainPage.openBaseUrl()
                .waitForFaqSection()
                .clickFaqQuestion(index);
        String expectedAnswers = mainPage.getFaqAnswerText(index);
        assertEquals(questions, expectedAnswers,
                "Текст ответа на вопрос №" + questionNumber + " должен совпадать с ожидаемым: " + questions);
    }
}

package util;

import java.time.LocalDate;

public class DateHelper {

    /**
     * Получить номер дня
     *
     * @param daysFromToday количество дней от сегодня
     */
    public static int getDayNumber(int daysFromToday) {
        return LocalDate.now().plusDays(daysFromToday).getDayOfMonth();
    }
}

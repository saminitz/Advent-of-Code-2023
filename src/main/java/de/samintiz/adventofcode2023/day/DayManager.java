package de.samintiz.adventofcode2023.day;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DayManager {

    private DayManager() {
    }

    @SuppressWarnings("unchecked")
    public static Day getDayInstance(int number) {
        try {
            Class<? extends Day> dayClass = (Class<? extends Day>) Class
                    .forName(formatDayNumberToClass(number));
            return dayClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException
                | ClassNotFoundException e) {
            throw new DayNotFoundException();
        }
    }

    public static List<Day> getAllPossibleDays() {
        List<Day> days = new ArrayList<>();

        // Not very pretty but idk a better way
        int maxDay = 31;
        for (int i = 0; i <= maxDay; i++) {
            try {
                days.add(getDayInstance(i));
            } catch (DayNotFoundException e) {
            }
        }
        return days;
    }

    public static boolean isDayImplemented(int number) {
        try {
            getDayInstance(number);
            return true;
        } catch (DayNotFoundException e) {
            return false;
        }
    }

    private static String formatDayNumberToClass(int number) {
        return String.format("de.samintiz.adventofcode2023.day%1$02d.Day%1$02d", number);
    }
}

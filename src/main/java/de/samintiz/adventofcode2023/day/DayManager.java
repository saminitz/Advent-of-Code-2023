package de.samintiz.adventofcode2023.day;

import java.util.ArrayList;
import java.util.List;

public class DayManager {

    private DayManager() {
    }

    public static Day getDayInstance(int number) {
        try {
            Class<?> unknownClass = Class.forName(formatDayNumberToClass(number));
            return Day.class.cast(unknownClass);
        } catch (ClassNotFoundException | ClassCastException e) {
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
                // Just ignore the error and don't add the requested day to the list because it
                // does not exists
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

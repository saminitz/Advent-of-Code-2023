package de.samintiz.adventofcode2023.day;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DayManager {

    private DayManager() {
    }

    public static Day getDayInstance(int number) {
        try {
            Class<?> unknownClass = Class.forName(formatDayNumberToClass(number));
            Object objInstance;
            objInstance = unknownClass.getDeclaredConstructor().newInstance();
            Day day = Day.class.cast(objInstance);
            day.init();
            return day;
        } catch (ClassNotFoundException | ClassCastException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            throw new DayNotFoundException();
        }
    }

    public static Map<Integer, Day> getAllPossibleDays() {
        Map<Integer, Day> days = new LinkedHashMap<>();

        // Not very pretty but idk a better way
        int maxDay = 31;
        for (int i = 0; i <= maxDay; i++) {
            try {
                days.put(i, getDayInstance(i));
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

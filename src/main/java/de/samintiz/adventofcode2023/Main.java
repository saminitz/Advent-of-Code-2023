package de.samintiz.adventofcode2023;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.day.DayManager;

public class Main {

    public static void main(String[] args) {
        int dayNumber = 2;// Ui.askUserForDayNumber();
        executeBothParts(dayNumber);
    }

    private static void executeBothParts(int dayNumber) {
        Day day = DayManager.getDayInstance(dayNumber);
        System.out.println(String.format("""

                Day %d:
                    Part 1: %s
                    Part 2: %s
                """, dayNumber,
                day.partOne(),
                day.partTwo()));
    }
}
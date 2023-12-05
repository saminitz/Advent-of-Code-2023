package de.samintiz.adventofcode2023;

import de.samintiz.adventofcode2023.day.DayManager;
import de.samintiz.adventofcode2023.ui.Ui;

public class Main {

    public static void main(String[] args) {
        int dayNumber = Ui.askUserForDayNumber();
        executeBothParts(dayNumber);
    }

    private static void executeBothParts(int dayNumber) {
        System.out.println(String.format("""

                Day %d:
                    Part 1: %s
                    Part 2: %s
                """, dayNumber,
                DayManager.getDayInstance(dayNumber).partOne(),
                DayManager.getDayInstance(dayNumber).partTwo()));
    }
}
package de.samintiz.adventofcode2023;

import java.util.concurrent.TimeUnit;

import de.samintiz.adventofcode2023.day.DayManager;
import de.samintiz.adventofcode2023.ui.Ui;

public class Main {

    public static void main(String[] args) {
        int dayNumber = Ui.askUserForDayNumber();
        executeBothParts(dayNumber);
    }

    private static void executeBothParts(int dayNumber) {
        long startTime = System.currentTimeMillis();

        String partOne = DayManager.getDayInstance(dayNumber).partOne();
        String partTwo = DayManager.getDayInstance(dayNumber).partTwo();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        long minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
        long millis = TimeUnit.MILLISECONDS.toMillis(elapsedTime);

        System.out.println(String.format("""

                Day %d:
                    Part 1: %s
                    Part 2: %s

                Time elapsed: %02d:%02d.%03d
                """,
                dayNumber,
                partOne,
                partTwo,
                minutes, seconds, millis));
    }
}
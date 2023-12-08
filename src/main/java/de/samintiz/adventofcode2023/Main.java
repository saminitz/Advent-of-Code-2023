package de.samintiz.adventofcode2023;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.samintiz.adventofcode2023.day.DayManager;
import de.samintiz.adventofcode2023.ui.Ui;

public class Main {

    public static void main(String[] args) {
        int dayNumber = Ui.askUserForDayNumber();
        executeBothParts(dayNumber);
    }

    private static void executeBothParts(int dayNumber) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        System.err.printf("%nTime Started: %s Uhr%n%n", formatter.format(date));

        long startTime = System.currentTimeMillis();

        String partOne = DayManager.getDayInstance(dayNumber).partOne();
        String partTwo = DayManager.getDayInstance(dayNumber).partTwo();

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println(String.format("""
                Day %d:
                    Part 1: %s
                    Part 2: %s

                Time elapsed: %s
                """,
                dayNumber,
                partOne,
                partTwo,
                convertMillisecondsToString(elapsedTime)));
    }

    private static String convertMillisecondsToString(long milliseconds) {
        long hours = TimeUnit.MILLISECONDS.toHours(milliseconds);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds);
        long millis = TimeUnit.MILLISECONDS.toMillis(milliseconds);
        return String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, millis);
    }
}
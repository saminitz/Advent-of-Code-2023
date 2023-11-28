package de.samintiz.adventofcode2023;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.day.DayManager;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int dayNumber = askUserForDayNumber();
        executeBothParts(dayNumber);
    }

    private static int askUserForDayNumber() {
        int input = -1;
        printPossibleDays();
        while (input == -1) {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Only numbers are allowed! Please choose again: ");
                scanner.next();
                continue;
            }
            if (!DayManager.isDayImplemented(input)) {
                input = -1;
                System.out.print("This day has not been solved yet! Please choose again: ");
            }
        }
        return input;
    }

    private static void printPossibleDays() {
        StringBuilder msgBuilder = new StringBuilder();
        msgBuilder.append("\n\nWelcome!\n");
        msgBuilder.append("Below are all implemented Days\n\n");

        for (Day day : DayManager.getAllPossibleDays()) {
            msgBuilder
                    .append(String.format("- Day %2d%n", day.getNumber()));
        }

        msgBuilder.append("\nPlease enter the day you want to see: ");
        System.out.print(msgBuilder);
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
package de.samintiz.adventofcode2023.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.day.DayManager;

public class Ui {
    private Ui() {
    }

    private static Scanner scanner = new Scanner(System.in);

    public static int askUserForDayNumber() {
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
}

package de.samintiz.adventofcode2023;

import java.util.InputMismatchException;
import java.util.Scanner;

import de.samintiz.adventofcode2023.day.TaskDay;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int day = askUserForDayNumber();
        TaskDay taskDay = TaskDay.of(day);
        executeBothParts(taskDay);
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
            if (!TaskDay.hasDay(input)) {
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

        for (TaskDay taskDay : TaskDay.values()) {
            msgBuilder
                    .append(String.format("%-4s %s%n", String.format("(%d)", taskDay.getNumber()), taskDay.toString()));
        }

        msgBuilder.append("\nPlease enter the day you want to see: ");
        System.out.print(msgBuilder);
    }

    private static void executeBothParts(TaskDay taskDay) {
        System.out.println(String.format("""

                Day %d:
                    Part 1: %s
                    Part 2: %s
                """, taskDay.getNumber(),
                taskDay.getNewInstance().init().partOne(),
                taskDay.getNewInstance().init().partTwo()));
    }
}
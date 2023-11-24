package de.samintiz.adventofcode2023.day;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import de.samintiz.adventofcode2023.setuptest.SetupTest;

public enum TaskDay {
    SETUP_TEST(0, SetupTest.class);
    // DAY_01(1, Day01.class),
    // DAY_02(2, Day02.class),
    // DAY_03(3, Day03.class),
    // DAY_04(4, Day04.class),
    // DAY_05(5, Day05.class),
    // DAY_06(6, Day06.class),
    // DAY_07(7, Day07.class),
    // DAY_08(8, Day08.class),
    // DAY_09(9, Day09.class),
    // DAY_10(10, Day10.class),
    // DAY_11(11, Day11.class),
    // DAY_12(12, Day12.class),
    // DAY_13(13, Day13.class),
    // DAY_14(14, Day14.class),
    // DAY_15(15, Day15.class),
    // DAY_16(16, Day16.class),
    // DAY_17(17, Day17.class),
    // DAY_18(18, Day18.class),
    // DAY_19(19, Day19.class),
    // DAY_20(20, Day20.class),
    // DAY_21(21, Day21.class),
    // DAY_22(22, Day22.class),
    // DAY_23(23, Day23.class),
    // DAY_24(24, Day24.class),
    // DAY_25(25, Day25.class);

    private int number;
    private Class<? extends Day> associatedClass;

    private TaskDay(int number, Class<? extends Day> dayClass) {
        this.number = number;
        this.associatedClass = dayClass;
    }

    public int getNumber() {
        return number;
    }

    public static TaskDay of(int number) {
        return Stream.of(TaskDay.values()).filter(d -> d.number == number).findFirst().orElseThrow();
    }

    public static boolean hasDay(int numbers) {
        return Stream.of(TaskDay.values()).anyMatch(d -> d.number == numbers);
    }

    public Day getNewInstance() {
        Day instance;
        try {
            instance = this.associatedClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException("The given Day class does not exists");
        }
        return instance;
    }

    @Override
    public String toString() {
        return String.format("Day %02d", this.number);
    }

}

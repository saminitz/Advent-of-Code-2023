package de.samintiz.adventofcode2023.day;

public interface Day {
    public default Day init() {
        return this;
    }

    public int getNumber();

    public default String partOne() {
        return "Not implemented!";
    }

    public default String partTwo() {
        return "Not implemented!";
    }
}

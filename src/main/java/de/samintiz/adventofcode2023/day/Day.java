package de.samintiz.adventofcode2023.day;

public interface Day {
    public default Day init() {
        return this;
    }

    public default String partOne() {
        return "Not implemented!\n\n";
    }

    public default String partTwo() {
        return "Not implemented!\n\n";
    }
}

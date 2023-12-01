package de.samintiz.adventofcode2023.day00;

import de.samintiz.adventofcode2023.day.Day;

public class Day00 implements Day {

    @Override
    public void init() {
        System.out.println("Test: init");
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public String partOne() {
        return "Test: " + this.hashCode() + "partOne";
    }

    @Override
    public String partTwo() {
        return "Test: " + this.hashCode() + "partTwo";
    }

}

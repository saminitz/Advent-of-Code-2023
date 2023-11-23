package de.samintiz.adventofcode2023.setuptest;

import de.samintiz.adventofcode2023.day.Day;

public class SetupTest implements Day {

    @Override
    public void init() {
        System.out.println("init");
    }

    @Override
    public String partOne() {
        return this.hashCode() + "partOne";
    }

    @Override
    public String partTwo() {
        return this.hashCode() + "partTwo";
    }

}

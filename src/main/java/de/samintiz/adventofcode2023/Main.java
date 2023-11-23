package de.samintiz.adventofcode2023;

import de.samintiz.adventofcode2023.day.DayFactory;
import de.samintiz.adventofcode2023.setuptest.SetupTest;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        DayFactory dayFactory = new DayFactory(SetupTest.class);

        System.out.println(dayFactory.newInstance().partOne());
        System.out.println(dayFactory.newInstance().partTwo());
    }
}
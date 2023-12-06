package de.samintiz.adventofcode2023.day00;

import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day00 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        return String.valueOf("Not implemented");
    }

    @Override
    public String partTwo() {
        return String.valueOf("Not implemented!");
    }

}

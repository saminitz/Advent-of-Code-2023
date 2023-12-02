package de.samintiz.adventofcode2023.day02;

import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day02 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.PART_ONE_TEST).readAllLines();
    }

    @Override
    public int getNumber() {
        return 2;
    }

    @Override
    public String partOne() {
        return "Not implemented!";
    }

    @Override
    public String partTwo() {
        return "Not implemented!";
    }

}

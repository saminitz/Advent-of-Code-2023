package de.samintiz.adventofcode2023.day01;

import java.nio.file.Path;
import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day01 implements Day {

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
    public String partOne() {
        Path inputPath = Path.of("src/main/java/de/samintiz/adventofcode2023/day01/inputPartOne.txt");
        List<String> allLines = InputReader.readAllLines(inputPath);

        long calibrationValueSum = allLines
                .stream()
                .map(line -> line.replaceAll("\\D", ""))
                .map(line -> line.substring(0, 1) + line.substring(line.length() - 1))
                .map(Integer::parseInt)
                .reduce(0, (a, b) -> a + b);

        return String.valueOf(calibrationValueSum);
    }

    @Override
    public String partTwo() {
        return "Not implemented!";
    }

}

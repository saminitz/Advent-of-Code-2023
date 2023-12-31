package de.samintiz.adventofcode2023.day01;

import java.util.Arrays;
import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day01 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        return sumCalibrationValueSum(allLines);
    }

    @Override
    public String partTwo() {
        List<String> convertedLines = convertAllSpelledNumbersToDigits(allLines);
        return sumCalibrationValueSum(convertedLines);
    }

    private String sumCalibrationValueSum(List<String> allLines) {
        long calibrationValueSum = allLines
                .stream()
                .map(line -> line.replaceAll("\\D", ""))
                .filter(line -> !line.isBlank())
                .map(line -> line.substring(0, 1) + line.substring(line.length() - 1))
                .map(Integer::parseInt)
                .reduce(0, (a, b) -> a + b);

        return String.valueOf(calibrationValueSum);
    }

    private List<String> convertAllSpelledNumbersToDigits(List<String> lines) {
        List<String> spelledNumbers = Arrays
                .asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        for (int lIndex = 0; lIndex < lines.size(); lIndex++) {
            for (int sIndex = 0; sIndex < spelledNumbers.size(); sIndex++) {
                String line = lines.get(lIndex);
                String spelledNumber = spelledNumbers.get(sIndex);
                String firstCharOfSpelledNumber = spelledNumber.substring(0, 1);
                String lastCharOfSpelledNumber = spelledNumber.substring(spelledNumber.length() - 1);
                String spelledNumberToDigitAndSurroundedWithFirstAndLastChar = line.replaceAll(
                        spelledNumbers.get(sIndex),
                        firstCharOfSpelledNumber + (sIndex + 1) + lastCharOfSpelledNumber);
                lines.set(lIndex, spelledNumberToDigitAndSurroundedWithFirstAndLastChar);
            }
        }

        return lines;
    }
}

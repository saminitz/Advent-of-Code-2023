package de.samintiz.adventofcode2023.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day09 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        List<List<Integer>> convertedLines = convertAllLines();
        int sum = convertedLines.stream()
                .map(lineValues -> calculateNextVal(generateAllDifferencesOfLineValues(lineValues)))
                .reduce(0, (a, b) -> a + b);
        return String.valueOf(sum);
    }

    @Override
    public String partTwo() {
        List<List<Integer>> convertedLines = convertAllLines();
        int sum = convertedLines.stream()
                .map(lineValues -> calculateNextValPartTwo(generateAllDifferencesOfLineValues(lineValues)))
                .reduce(0, (a, b) -> a + b);
        return String.valueOf(sum);
    }

    private int calculateNextValPartTwo(List<List<Integer>> allDifferences) {
        allDifferences.get(allDifferences.size() - 1).add(0, 0);
        for (int i = allDifferences.size() - 2; i >= 0; i--) {
            List<Integer> currentLine = allDifferences.get(i);
            List<Integer> belowLine = allDifferences.get(i + 1);

            int newValue = currentLine.get(0) - belowLine.get(0);
            currentLine.add(0, newValue);
        }

        return allDifferences.get(0).get(0);
    }

    private int calculateNextVal(List<List<Integer>> allDifferences) {
        allDifferences.get(allDifferences.size() - 1).add(0);
        for (int i = allDifferences.size() - 2; i >= 0; i--) {
            List<Integer> currentLine = allDifferences.get(i);
            List<Integer> belowLine = allDifferences.get(i + 1);

            int currentLineLastIndex = currentLine.size() - 1;
            int belowLineLastIndex = belowLine.size() - 1;

            int newValue = currentLine.get(currentLineLastIndex) + belowLine.get(belowLineLastIndex);
            currentLine.add(newValue);
        }

        return allDifferences.get(0).get(allDifferences.get(0).size() - 1);
    }

    private List<List<Integer>> generateAllDifferencesOfLineValues(List<Integer> lineValues) {
        List<List<Integer>> allDifferences = new ArrayList<>();
        allDifferences.add(lineValues);

        while (!allDifferences.get(allDifferences.size() - 1).stream().allMatch(x -> x == 0)) {
            List<Integer> currentValues = allDifferences.get(allDifferences.size() - 1);
            List<Integer> newDifferences = new ArrayList<>();
            for (int i = 0; i < currentValues.size() - 1; i++) {
                int difference = currentValues.get(i + 1) - currentValues.get(i);
                newDifferences.add(difference);
            }
            allDifferences.add(newDifferences);
        }

        return allDifferences;
    }

    private List<List<Integer>> convertAllLines() {
        List<List<Integer>> convertedLines = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("[-\\d]+");
        for (String line : allLines) {
            List<Integer> convertedLine = new ArrayList<>();
            Matcher numberMatcher = numberPattern.matcher(line);
            while (numberMatcher.find()) {
                convertedLine.add(Integer.parseInt(numberMatcher.group()));
            }
            convertedLines.add(convertedLine);
        }
        return convertedLines;
    }
}

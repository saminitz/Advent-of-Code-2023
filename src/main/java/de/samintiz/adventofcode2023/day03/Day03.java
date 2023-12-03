package de.samintiz.adventofcode2023.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day03 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        return String.valueOf(getCombinedNumbers(1));
    }

    @Override
    public String partTwo() {
        return String.valueOf(getCombinedNumbers(2));
    }

    private int getCombinedNumbers(int part) {
        int sum = 0;
        Pattern symbolPattern;

        if (part == 1) {
            symbolPattern = Pattern.compile("[^\\d\\.]");
        } else
            symbolPattern = Pattern.compile("\\*");

        for (int i = 0; i < allLines.size(); i++) {
            Matcher symbolMatcher = symbolPattern.matcher(allLines.get(i));
            while (symbolMatcher.find()) {
                int indexOfSymbol = symbolMatcher.start();
                List<Integer> numbers = new ArrayList<>();
                // line above
                if (i > 0) {
                    numbers.addAll(getCenterLeftAndRightNumbers(allLines.get(i - 1), indexOfSymbol));
                }
                // current line
                numbers.addAll(getCenterLeftAndRightNumbers(allLines.get(i), indexOfSymbol));
                // line below
                if (i < allLines.size()) {
                    numbers.addAll(getCenterLeftAndRightNumbers(allLines.get(i + 1), indexOfSymbol));
                }

                numbers = numbers.stream().filter(num -> num != 0).toList();

                if (part == 1) {
                    sum += numbers.stream().reduce(0, (a, b) -> a + b);
                } else if (part == 2 && numbers.size() == 2) {
                    sum += numbers.stream().reduce(1, (a, b) -> a * b);
                }

            }
        }

        return sum;
    }

    private List<Integer> getCenterLeftAndRightNumbers(String line, int index) {
        if (Character.isDigit(line.charAt(index))) {
            int startIndex = getStartingIndex(line, index);
            return Arrays.asList(getForwardNumber(line, startIndex));
        } else {
            List<Integer> numbers = new ArrayList<>();
            // right
            int forwardIndex = index + 1;
            numbers.add(getForwardNumber(line, forwardIndex));

            // left
            int reverseIndex = getStartingIndex(line, index);
            numbers.add(getForwardNumber(line, reverseIndex));

            return numbers;
        }
    }

    private int getStartingIndex(String line, int startIndex) {
        while (startIndex > 0 && Character.isDigit(line.charAt(startIndex - 1))) {
            startIndex--;
        }
        return startIndex;
    }

    private int getForwardNumber(String line, int forwardIndex) {
        StringBuilder forwardNumberBuilder = new StringBuilder();
        while (forwardIndex < line.length() && Character.isDigit(line.charAt(forwardIndex))) {
            forwardNumberBuilder.append(line.charAt(forwardIndex));
            forwardIndex++;
        }
        String forwardNumber = forwardNumberBuilder.toString();
        return Integer.parseInt(forwardNumber.isEmpty() ? "0" : forwardNumber);
    }
}

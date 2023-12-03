package de.samintiz.adventofcode2023.day03;

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
        return String.valueOf(getSurroundedSum());
    }

    @Override
    public String partTwo() {
        return "Not implemented!";
    }

    private int getSurroundedSum() {
        int sum = 0;

        Pattern symbolPattern = Pattern.compile("[^\\d\\.]");

        for (int i = 0; i < allLines.size(); i++) {
            Matcher symbolMatcher = symbolPattern.matcher(allLines.get(i));
            while (symbolMatcher.find()) {
                int indexOfSymbol = symbolMatcher.start();
                // line above
                if (i > 0) {
                    sum += sumCenterLeftAndRightDigits(allLines.get(i - 1), indexOfSymbol);
                }
                // current line
                sum += sumCenterLeftAndRightDigits(allLines.get(i), indexOfSymbol);
                // line below
                if (i < allLines.size()) {
                    sum += sumCenterLeftAndRightDigits(allLines.get(i + 1), indexOfSymbol);
                }
            }
        }

        return sum;
    }

    private int sumCenterLeftAndRightDigits(String line, int index) {
        if (Character.isDigit(line.charAt(index))) {
            int startIndex = getStartingIndex(line, index);
            return getForwardSum(line, startIndex);
        } else {
            // right
            int forwardIndex = index + 1;
            int forwardSum = getForwardSum(line, forwardIndex);

            // left
            int reverseIndex = getStartingIndex(line, index);
            int reverseSum = getForwardSum(line, reverseIndex);

            return forwardSum + reverseSum;
        }
    }

    private int getStartingIndex(String line, int startIndex) {
        while (startIndex > 0 && Character.isDigit(line.charAt(startIndex - 1))) {
            startIndex--;
        }
        return startIndex;
    }

    private int getForwardSum(String line, int forwardIndex) {
        StringBuilder forwardNumberBuilder = new StringBuilder();
        while (forwardIndex < line.length() && Character.isDigit(line.charAt(forwardIndex))) {
            forwardNumberBuilder.append(line.charAt(forwardIndex));
            forwardIndex++;
        }
        String forwardNumber = forwardNumberBuilder.toString();
        return Integer.parseInt(forwardNumber.isEmpty() ? "0" : forwardNumber);
    }
}

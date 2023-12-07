package de.samintiz.adventofcode2023.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day07 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        List<Hand> allHands = convertAllLines();
        allHands.sort(Hand::compareTo);

        long totalWinnings = IntStream.range(1, allHands.size() + 1).mapToLong(i -> allHands.get(i - 1).getBid() * i)
                .reduce(0, (a, b) -> a + b);

        // return String.valueOf(allHands.get(0).compareTo(allHands.get(3)));
        return String.valueOf(totalWinnings);
    }

    @Override
    public String partTwo() {
        return String.valueOf("Not implemented!");
    }

    private List<Hand> convertAllLines() {
        List<Hand> hands = new ArrayList<>();

        Pattern numberPattern = Pattern.compile("\\w+");

        for (String line : allLines) {
            Matcher numberMatcher = numberPattern.matcher(line);

            numberMatcher.find();
            String cardsString = numberMatcher.group();

            numberMatcher.find();
            int bid = Integer.parseInt(numberMatcher.group());

            hands.add(new Hand(cardsString, bid));
        }

        return hands;
    }
}

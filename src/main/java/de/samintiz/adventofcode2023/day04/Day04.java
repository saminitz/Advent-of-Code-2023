package de.samintiz.adventofcode2023.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day04 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        List<Card> allCards = convertAllLinesToCards();
        return String.valueOf(getSummedCardPoints(allCards));
    }

    @Override
    public String partTwo() {
        List<Card> allCards = convertAllLinesToCards();
        return String.valueOf(getTotalScratchcardsWithRules(allCards));
    }

    public int getTotalScratchcardsWithRules(List<Card> cards) {
        int originalCardSize = cards.size();
        for (int i = 0; i < originalCardSize; i++) {
            // Don't need to search for the id because the new ones get appended in the end
            long matchingCount = cards.get(i).getMatchingCount();
            Card currentCard = cards.get(i);
            for (int j = 1; j <= matchingCount; j++) {
                int newId = i + j + 1;
                long count = cards.stream().filter(card -> card.id() == currentCard.id()).count();
                Card newCard = cards.stream().filter(card -> card.id() == newId).findFirst().get();
                for (int k = 0; k < count; k++) {
                    cards.add(new Card(newId, newCard.winningNumbers(), newCard.chosenNumbers()));
                }
            }
        }
        return cards.size();
    }

    private int getSummedCardPoints(List<Card> cards) {
        return cards.stream().map(Card::getPoints).reduce(0, (a, b) -> a + b);
    }

    private List<Card> convertAllLinesToCards() {
        List<Card> cards = new ArrayList<>();

        for (String line : allLines) {
            line = line.replaceAll("\\s{2,}", " ");
            Pattern idRegex = Pattern.compile("\\d+");
            Matcher idMatcher = idRegex.matcher(line);
            idMatcher.find();
            int id = Integer.parseInt(idMatcher.group(0));

            Pattern winningNumbersRegex = Pattern.compile("(?<=:\\s).*(?=\\s\\|)");
            Matcher winningNumbersMatcher = winningNumbersRegex.matcher(line);
            List<Integer> winningNumbers = new ArrayList<>();
            winningNumbersMatcher.find();
            Arrays.asList(winningNumbersMatcher.group().split(" "))
                    .forEach(num -> winningNumbers.add(Integer.parseInt(num)));

            Pattern chosenNumbersRegex = Pattern.compile("(?<=\\|\\s).*$");
            Matcher chosenNumbersMatcher = chosenNumbersRegex.matcher(line);
            List<Integer> chosenNumbers = new ArrayList<>();
            chosenNumbersMatcher.find();
            Arrays.asList(chosenNumbersMatcher.group().split(" "))
                    .forEach(num -> chosenNumbers.add(Integer.parseInt(num)));

            cards.add(new Card(id, winningNumbers, chosenNumbers));
        }

        return cards;
    }
}

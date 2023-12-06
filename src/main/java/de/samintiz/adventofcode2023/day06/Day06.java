package de.samintiz.adventofcode2023.day06;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day06 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        List<TimeDistance> timeDistances = convertAllLines();
        List<Long> numberOfPossibleWins = getNumberOfPossibleWins(timeDistances);
        return String.valueOf(numberOfPossibleWins.stream().reduce(1L, (a, b) -> a * b));
    }

    @Override
    public String partTwo() {
        return String.valueOf("Not implemented");
    }

    private List<Long> getNumberOfPossibleWins(List<TimeDistance> timeDistances) {
        List<Long> numberOfPossibleWins = new ArrayList<>();

        for (TimeDistance timeDistance : timeDistances) {
            long possibleWins = 0;
            for (long i = 1; i <= timeDistance.time(); i++) {
                long remainingTime = timeDistance.time() - i;
                long calculatedDistance = i * remainingTime;
                if (calculatedDistance > timeDistance.distance()) {
                    possibleWins++;
                }
            }
            numberOfPossibleWins.add(possibleWins);
        }

        return numberOfPossibleWins;
    }

    private List<TimeDistance> convertAllLines() {
        List<TimeDistance> timeDistances = new ArrayList<>();

        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher timeMatcher = numberPattern.matcher(allLines.get(0));
        Matcher distanceMatcher = numberPattern.matcher(allLines.get(1));

        while (timeMatcher.find()) {
            distanceMatcher.find();
            timeDistances.add(
                    new TimeDistance(Long.parseLong(timeMatcher.group()), Long.parseLong(distanceMatcher.group())));
        }

        return timeDistances;
    }
}

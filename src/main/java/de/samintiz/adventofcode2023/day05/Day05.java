package de.samintiz.adventofcode2023.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day05 implements Day {

    private List<String> allLines;
    List<Long> seeds;
    List<SourceDestinationMap> sourceDestinationMaps;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        convertInput();
        return String.valueOf(getLowestLocationOfSeeds());
    }

    @Override
    public String partTwo() {
        return String.valueOf("Not implemented!");
    }

    private long getLowestLocationOfSeeds() {
        long smallestLocation = Long.MAX_VALUE;

        for (Long seed : seeds) {
            long id = seed;
            String destinationName = "seed";

            while (!destinationName.equals("location")) {
                long finalId = id;
                String finalDestinationName = destinationName;
                Optional<SourceDestinationMap> sourceDestinationMapOptional = sourceDestinationMaps.stream()
                        .filter(x -> x.sourceName().equals(finalDestinationName) && x.isSourceIdInRange(finalId))
                        .findFirst();

                if (sourceDestinationMapOptional.isPresent()) {
                    SourceDestinationMap sourceDestinationMap = sourceDestinationMapOptional.get();
                    destinationName = sourceDestinationMap.destinationName();
                    id = sourceDestinationMap.getDestinationIdOfSourceId(finalId);
                } else {
                    destinationName = sourceDestinationMaps.stream()
                            .filter(x -> x.sourceName().equals(finalDestinationName))
                            .findFirst().get().destinationName();
                }
            }

            if (id < smallestLocation) {
                smallestLocation = id;
            }
        }
        return smallestLocation;
    }

    private void convertInput() {
        allLines = allLines.stream().filter(line -> !line.isBlank()).toList();

        seeds = getNumbersOfLine(allLines.get(0));
        sourceDestinationMaps = new ArrayList<>();

        String sourceName = null;
        String destinationName = null;
        Pattern sourceNamePattern = Pattern.compile("\\w+(?=-to)");
        Pattern destinationNamePattern = Pattern.compile("\\w+(?=\\smap:)");

        for (int i = 1; i < allLines.size(); i++) {
            String line = allLines.get(i);

            Matcher sourceNameMatcher = sourceNamePattern.matcher(line);
            Matcher destinationNameMatcher = destinationNamePattern.matcher(line);

            if (sourceNameMatcher.find()) {
                sourceName = sourceNameMatcher.group();
                destinationNameMatcher.find();
                destinationName = destinationNameMatcher.group();
                continue;
            }

            List<Long> numbers = getNumbersOfLine(line);
            sourceDestinationMaps.add(new SourceDestinationMap(sourceName, destinationName, numbers.get(0),
                    numbers.get(1), numbers.get(2)));
        }
    }

    private List<Long> getNumbersOfLine(String line) {
        List<Long> numbers = new ArrayList<>();
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher numberMatcher = numberPattern.matcher(line);
        while (numberMatcher.find()) {
            numbers.add(Long.parseLong(numberMatcher.group()));
        }
        return numbers;
    }
}

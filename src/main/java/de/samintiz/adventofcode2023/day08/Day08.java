package de.samintiz.adventofcode2023.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day08 implements Day {

    private List<String> allLines;
    boolean[] instructions;
    Map<String, Pair> directionsMap;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
        directionsMap = new HashMap<>();
    }

    @Override
    public String partOne() {
        convertAllLines();
        return String.valueOf(countStepsToEnd("AAA", "ZZZ", Object::equals));
    }

    @Override
    public String partTwo() {
        convertAllLines();
        return String.valueOf(countStepsToEndParallel());
    }

    private long countStepsToEndParallel() {
        List<String> currentKeys = new ArrayList<>();
        List<Long> stepsToZ = new ArrayList<>();
        currentKeys.addAll(directionsMap.keySet().stream().filter(key -> key.endsWith("A")).toList());

        stepsToZ.addAll(currentKeys.stream().map(key -> countStepsToEnd(key, "", (a, b) -> a.endsWith("Z"))).toList());

        return LCDFinder.findLeastCommonDenominator(stepsToZ);
    }

    private long countStepsToEnd(String currentKey, String endKey, BiPredicate<String, String> compare) {
        int index = 0;
        int stepCount = 0;

        while (!compare.test(currentKey, endKey)) {
            stepCount++;
            if (instructions[index]) {
                // Left
                index = (index + 1) % (instructions.length);
                currentKey = directionsMap.get(currentKey).left();
            } else {
                // Right
                index = (index + 1) % (instructions.length);
                currentKey = directionsMap.get(currentKey).right();
            }
        }

        return stepCount;
    }

    private void convertAllLines() {
        String instructionsLine = allLines.get(0);
        instructions = new boolean[instructionsLine.length()];
        for (int i = 0; i < instructionsLine.length(); i++) {
            if (instructionsLine.charAt(i) == 'L') {
                instructions[i] = true;
            } else {
                instructions[i] = false;
            }
        }

        Pattern keyPattern = Pattern.compile("\\w+");
        for (int i = 2; i < allLines.size(); i++) {
            String line = allLines.get(i);

            Matcher keyMatcher = keyPattern.matcher(line);
            keyMatcher.find();
            String key0 = keyMatcher.group();
            keyMatcher.find();
            String key1 = keyMatcher.group();
            keyMatcher.find();
            String key2 = keyMatcher.group();
            directionsMap.put(key0, new Pair(key1, key2));
        }
    }
}

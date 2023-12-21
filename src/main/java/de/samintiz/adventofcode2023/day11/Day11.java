package de.samintiz.adventofcode2023.day11;

import java.util.ArrayList;
import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day11 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        expandAllUniverse();
        return String.valueOf(calculateSumOfEveryGalaxPair(getCoordsOfGalaxies()));
    }

    @Override
    public String partTwo() {
        return String.valueOf("Not implemented!");
    }

    private long calculateSumOfEveryGalaxPair(List<Point> pointList) {
        long sum = 0;
        for (int i = 0; i < pointList.size(); i++) {
            Point a = pointList.get(i);
            for (int j = i + 1; j < pointList.size(); j++) {
                Point b = pointList.get(j);
                sum += Math.abs(b.x() - a.x()) + Math.abs(b.y() - a.y());
            }
        }
        return sum;
    }

    private List<Point> getCoordsOfGalaxies() {
        List<Point> pointList = new ArrayList<>();

        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < allLines.get(y).length(); x++) {
                if (allLines.get(y).charAt(x) == '#') {
                    pointList.add(new Point(x, y));
                }
            }
        }

        return pointList;
    }

    private void expandAllUniverse() {
        List<String> expandedLines = new ArrayList<>(allLines);

        for (int x = allLines.get(0).length() - 1; x >= 0; x--) {
            boolean hasHashtag = false;
            for (int y = 0; y < allLines.size(); y++) {
                if (allLines.get(y).charAt(x) == '#') {
                    hasHashtag = true;
                    break;
                }
            }
            if (!hasHashtag) {
                duplicateVerticalLine(expandedLines, x);
            }
        }

        // horizontal direction
        for (int y = 0; y < expandedLines.size(); y++) {
            if (!expandedLines.get(y).contains("#")) {
                expandedLines.add(y, expandedLines.get(y));
                y++;
            }
        }

        allLines = expandedLines;
    }

    private void duplicateVerticalLine(List<String> expandedLines, int horizontalIndex) {
        for (int y = 0; y < expandedLines.size(); y++) {
            expandedLines.set(y, expandedLines.get(y).substring(0, horizontalIndex) + "."
                    + expandedLines.get(y).substring(horizontalIndex));
        }
    }

}

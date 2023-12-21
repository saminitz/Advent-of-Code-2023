package de.samintiz.adventofcode2023.day11;

import java.util.ArrayList;
import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day11 implements Day {

    private List<String> allLines;
    private List<Integer> yExpansions;
    private List<Integer> xExpansions;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
        yExpansions = new ArrayList<>();
        xExpansions = new ArrayList<>();
    }

    @Override
    public String partOne() {
        fillExpansionsArrays();
        return String.valueOf(calculateSumOfEveryGalaxPair(getCoordsOfGalaxies(1)));
    }

    @Override
    public String partTwo() {
        fillExpansionsArrays();
        return String.valueOf(calculateSumOfEveryGalaxPair(getCoordsOfGalaxies(1000000)));
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

    private List<Point> getCoordsOfGalaxies(int sizeExpansion) {
        List<Point> pointList = new ArrayList<>();
        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < allLines.get(y).length(); x++) {
                if (allLines.get(y).charAt(x) == '#') {
                    pointList.add(expandPoint(new Point(x, y), sizeExpansion));
                }
            }
        }

        return pointList;
    }

    private Point expandPoint(Point original, int sizeExpansion) {
        int newX = scaleAxis(original.x(), sizeExpansion, xExpansions);
        int newY = scaleAxis(original.y(), sizeExpansion, yExpansions);
        return new Point(newX, newY);
    }

    private int scaleAxis(int axis, int sizeExpansion, List<Integer> expansionsArr) {
        for (int i = expansionsArr.size() - 1; i >= 0; i--) {
            if (axis > expansionsArr.get(i)) {
                return axis + ((sizeExpansion - 1) * (i + 1));
            }
        }
        return axis;
    }

    private void fillExpansionsArrays() {
        for (int x = 0; x < allLines.get(0).length(); x++) {
            boolean hasHashtag = false;
            for (int y = 0; y < allLines.size(); y++) {
                if (allLines.get(y).charAt(x) == '#') {
                    hasHashtag = true;
                    break;
                }
            }
            if (!hasHashtag) {
                xExpansions.add(x);
            }
        }

        // horizontal direction
        for (int y = 0; y < allLines.size(); y++) {
            if (!allLines.get(y).contains("#")) {
                yExpansions.add(y);
            }
        }
    }
}

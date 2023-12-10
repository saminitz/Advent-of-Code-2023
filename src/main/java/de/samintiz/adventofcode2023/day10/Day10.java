package de.samintiz.adventofcode2023.day10;

import java.util.List;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day10 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public String partOne() {
        return String.valueOf(sumPathSteps());
    }

    @Override
    public String partTwo() {
        return String.valueOf("Not implemented!");
    }

    public long sumPathSteps() {
        Point startingPosition = getStartingPosition();
        Point lastDirection = getStartingDirection(startingPosition);
        Point currentPoint = startingPosition.add(lastDirection);
        long pathSteps = 0L;
        while (!startingPosition.equals(currentPoint) || pathSteps == 0) {
            char c = allLines.get(currentPoint.y()).charAt(currentPoint.x());
            Point nextDirection = Direction.of(c).getNextDirection(lastDirection);

            currentPoint = currentPoint.add(nextDirection);
            pathSteps++;

            lastDirection = nextDirection;
        }
        pathSteps++;
        return pathSteps / 2;
    }

    private Point getStartingDirection(Point startingPosition) {
        int x = startingPosition.x();
        int y = startingPosition.y();

        // Left
        if (x > 0) {
            char leftChar = allLines.get(y).charAt(x - 1);
            if (leftChar == '-' || leftChar != 'F' || leftChar != 'L') {
                return new Point(-1, 0);
            }
        } else if (x + 1 < allLines.get(y).length()) {
            char rightChar = allLines.get(y).charAt(x + 1);
            if (rightChar == '-' || rightChar != 'J' || rightChar != '7') {
                return new Point(1, 0);
            }
        } else if (y > 0) {
            char topChar = allLines.get(y - 1).charAt(x);
            if (topChar == '|' || topChar != 'F' || topChar != '7') {
                return new Point(-1, 0);
            }
        } else if (y + 1 < allLines.size()) {
            char bottomChar = allLines.get(y + 1).charAt(x);
            if (bottomChar == '|' || bottomChar != 'J' || bottomChar != '7') {
                return new Point(-1, 0);
            }
        }

        throw new NotFoundException();
    }

    private Point getStartingPosition() {
        for (int y = 0; y < allLines.size(); y++) {
            for (int x = 0; x < allLines.get(y).length(); x++) {
                if (allLines.get(y).charAt(x) == 'S') {
                    return new Point(x, y);
                }
            }
        }
        throw new NotFoundException();
    }

}

package de.samintiz.adventofcode2023.day10;

import java.util.Map;
import java.util.stream.Stream;

public enum Direction {

    START(Map.of(), 'S'),
    NORTH_SOUTH(Map.of(new Point(0, 1), new Point(0, 1), new Point(0, -1), new Point(0, -1)), '|'),
    EAST_WEST(Map.of(new Point(1, 0), new Point(1, 0), new Point(-1, 0), new Point(-1, 0)), '-'),
    NORTH_EAST(Map.of(new Point(0, 1), new Point(1, 0), new Point(-1, 0), new Point(0, -1)), 'L'),
    NORTH_WEST(Map.of(new Point(1, 0), new Point(0, -1), new Point(0, 1), new Point(-1, 0)), 'J'),
    SOUTH_WEST(Map.of(new Point(1, 0), new Point(0, 1), new Point(0, -1), new Point(-1, 0)), '7'),
    SOUTH_EAST(Map.of(new Point(0, -1), new Point(1, 0), new Point(-1, 0), new Point(0, 1)), 'F');

    private Map<Point, Point> previousDirToNext;
    private Character symbol;

    Direction(Map<Point, Point> previousDirToNext, Character symbol) {
        this.previousDirToNext = previousDirToNext;
        this.symbol = symbol;
    }

    public static Direction of(Character symbol) {
        return Stream.of(Direction.values()).filter(dir -> dir.symbol.equals(symbol)).findFirst().orElseThrow();
    }

    public Point getNextDirection(Point previousDirection) {
        return previousDirToNext.get(previousDirection);
    }
}

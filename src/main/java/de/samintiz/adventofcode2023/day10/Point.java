package de.samintiz.adventofcode2023.day10;

public record Point(int x, int y) {
    public Point add(Point o) {
        return new Point(this.x + o.x, this.y + o.y);
    }
}

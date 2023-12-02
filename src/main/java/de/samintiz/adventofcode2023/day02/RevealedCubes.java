package de.samintiz.adventofcode2023.day02;

record RevealedCubes(int red, int green, int blue) {
    RevealedCubes getSmallestOfBothRevealedCubes(RevealedCubes other) {
        return new RevealedCubes(
                other.red == 0 || (this.red != 0 && this.red > other.red) ? this.red : other.red,
                other.green == 0 || (this.green != 0 && this.green > other.green) ? this.green : other.green,
                other.blue == 0 || (this.blue != 0 && this.blue > other.blue) ? this.blue : other.blue);
    }
}
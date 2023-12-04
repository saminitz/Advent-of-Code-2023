package de.samintiz.adventofcode2023.day04;

import java.util.List;

public record Card(int id, List<Integer> winningNumbers, List<Integer> chosenNumbers) {
    public int getPoints() {
        long count = chosenNumbers.stream().filter(winningNumbers::contains).count() - 1;
        return Math.max(0, 1 << count);
    }

    public long numberOfMatches() {
        return chosenNumbers.stream().filter(winningNumbers::contains).count();
    }
}

package de.samintiz.adventofcode2023.day07;

import java.util.Arrays;
import java.util.List;

public record Card(Character value) implements Comparable<Card> {
    public static final List<Character> orderedCharacters = Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6',
            '5', '4', '3', '2');

    @Override
    public int compareTo(Card o) {
        return orderedCharacters.indexOf(value) - orderedCharacters.indexOf(o.value);
    }
}

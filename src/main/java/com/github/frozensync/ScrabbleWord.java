package com.github.frozensync;

import java.util.Map;
import java.util.Set;

/**
 * Representation of a word as used in the game Scrabble.
 */
class ScrabbleWord implements Comparable<ScrabbleWord> {

    private static final Map<Character, Integer> POINTS_MAP = Map.of(
            'O', 4, 'R', 6, 'D', 2, 'I', 3, 'N', 2, 'A', 1
    );
    private static final Set<Character> VALID_CHARS = POINTS_MAP.keySet();

    private final String value;
    private final boolean valid;

    ScrabbleWord(String word) {
        this.value = word.toUpperCase();
        this.valid = !value.isBlank() && value.chars()
                .mapToObj(c -> (char) c)
                .allMatch(VALID_CHARS::contains);
    }

    /**
     * Returns whether this {@code ScrabbleWord} is valid, including the additional Ordina rules.
     *
     * @return {@code true} if this {@code ScrabbleWord} is valid; otherwise {@code false}
     */
    boolean isValid() {
        return valid;
    }

    @Override
    public int compareTo(ScrabbleWord other) {
        return Integer.compare(this.toPoints(), other.toPoints());
    }

    private int toPoints() {
        if (!valid) return -1;

        return value.chars()
                .mapToObj(c -> (char) c)
                .mapToInt(POINTS_MAP::get)
                .sum();
    }

    @Override
    public String toString() {
        return value;
    }
}

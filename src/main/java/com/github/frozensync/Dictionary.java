package com.github.frozensync;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Representation of a dictionary containing a list of words.
 */
class Dictionary {

    private List<String> words;

    private Dictionary(List<String> words) {
        this.words = words;
    }

    /**
     * Returns a dictionary {@code Dictionary} constructed from the given {@code file}.
     *
     * @param file CSV file to construct the dictionary from
     * @return a dictionary
     * @throws FileNotFoundException if source is not found
     */
    static Dictionary from(File file) throws FileNotFoundException {
        var scanner = new Scanner(file);
        var words = new ArrayList<String>();

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            var word = parseLine(line);
            words.add(word);
        }

        return new Dictionary(words);
    }

    private static String parseLine(String line) {
        var scanner = new Scanner(line).useDelimiter(";");
        return scanner.next();
    }

    /**
     * Returns the words in this dictionary as a stream {@code Stream<String>}.
     *
     * @return the words in this dictionary
     */
    Stream<String> getWordsAsStream() {
        return words.stream();
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "words=" + words +
                '}';
    }
}

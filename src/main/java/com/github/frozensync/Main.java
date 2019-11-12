package com.github.frozensync;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    private static final String PATH_TO_DICTIONARY = "src/main/resources/opentaal-210g-verschillen.csv";

    public static void main(String[] args) throws FileNotFoundException {
        new Main().start();
    }

    private void start() throws FileNotFoundException {
        var dictionaryCsv = new File(PATH_TO_DICTIONARY);
        var dictionary = Dictionary.from(dictionaryCsv);

        dictionary.getWordsAsStream()
                .map(ScrabbleWord::new)
                .filter(ScrabbleWord::isValid)
                .max(ScrabbleWord::compareTo)
                .map(ScrabbleWord::toString)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Dictionary does not contain any valid Scrabble words.")
                );
    }
}

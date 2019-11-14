package com.github.frozensync;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    private static final Path PATH_TO_DICTIONARY = Path.of("src/main/resources/opentaal-210g-verschillen.csv");

    public static void main(String[] args) throws IOException {
        new Main().start();
    }

    private void start() throws IOException {
        Files.lines(PATH_TO_DICTIONARY)
                .map(this::parseLine)
                .map(ScrabbleWord::new)
                .filter(ScrabbleWord::isValid)
                .max(ScrabbleWord::compareTo)
                .map(ScrabbleWord::toString)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Dictionary does not contain any valid Scrabble words.")
                );
    }

    private String parseLine(String line) {
        var scanner = new Scanner(line).useDelimiter(";");
        return scanner.next();
    }
}

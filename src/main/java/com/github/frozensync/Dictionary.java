package com.github.frozensync;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class Dictionary {

    private List<String> words;

    private Dictionary(List<String> words) {
        this.words = words;
    }

    static Optional<Dictionary> from(File file) {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        var words = new ArrayList<String>();

        while (scanner.hasNext()) {
            var line = scanner.nextLine();
            String word = parseLine(line);
            words.add(word);
        }

        Dictionary result = new Dictionary(words);
        return Optional.of(result);
    }

    private static String parseLine(String line) {
        var scanner = new Scanner(line);
        scanner.useDelimiter(";");

        return scanner.next();
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "words=" + words +
                '}';
    }
}

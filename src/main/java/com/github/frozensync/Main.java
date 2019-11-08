package com.github.frozensync;

import java.io.File;

public class Main {

    private static final String PATH_TO_DICTIONARY = "src/main/resources/opentaal-210g-verschillen.csv";

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        var dictionaryCsv = new File(PATH_TO_DICTIONARY);
        var dictionary = Dictionary.from(dictionaryCsv).orElseThrow(() -> new IllegalStateException("Cannot continue without a valid dictionary"));

        System.out.println(dictionary);
    }
}

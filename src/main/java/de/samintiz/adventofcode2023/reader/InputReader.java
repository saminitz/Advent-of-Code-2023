package de.samintiz.adventofcode2023.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class InputReader {
    private InputReader() {
    }

    public static List<String> readAllLines(Path inputPath) {
        try {
            return Files.readAllLines(inputPath);
        } catch (IOException e) {
            System.out.printf("Unable to read input file: %s%n", inputPath.toString());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}

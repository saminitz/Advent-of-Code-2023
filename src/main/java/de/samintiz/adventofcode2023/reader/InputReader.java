package de.samintiz.adventofcode2023.reader;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import de.samintiz.adventofcode2023.day.Day;

public class InputReader {
    private Path file;

    public InputReader(Day day, InputFile inputFile) {
        String dayClassName = day.getClass().getName();
        Path packagePath = Path.of("src/main/java", dayClassName.replace(".", "/")).getParent();
        this.file = Path.of(packagePath.toString(), inputFile.getFileName());
    }

    public List<String> readAllLines() {
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            System.out.printf("Unable to read input file: %s%n", file.toString());
            throw new UncheckedIOException(e);
        }
    }
}

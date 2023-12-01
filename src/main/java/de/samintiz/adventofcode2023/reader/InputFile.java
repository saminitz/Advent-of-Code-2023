package de.samintiz.adventofcode2023.reader;

public enum InputFile {
    NORMAL("input.txt"),
    PART_ONE_TEST("partOneTest.txt"),
    PART_TWO_TEST("partTwoTest.txt");

    private String fileName;

    InputFile(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}

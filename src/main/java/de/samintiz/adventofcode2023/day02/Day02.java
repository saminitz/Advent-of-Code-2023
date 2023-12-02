package de.samintiz.adventofcode2023.day02;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.samintiz.adventofcode2023.day.Day;
import de.samintiz.adventofcode2023.reader.InputFile;
import de.samintiz.adventofcode2023.reader.InputReader;

public class Day02 implements Day {

    private List<String> allLines;

    @Override
    public void init() {
        allLines = new InputReader(this, InputFile.INPUT).readAllLines();
    }

    @Override
    public int getNumber() {
        return 2;
    }

    @Override
    public String partOne() {
        RevealedCubes requirement = new RevealedCubes(12, 13, 14);
        return String.valueOf(sumPossibleGameIds(requirement));
    }

    @Override
    public String partTwo() {
        List<Game> allGames = convertAllLinesToGameList();
        List<List<RevealedCubes>> allRevealedCubes = allGames
                .stream()
                .map(Game::revealedCubes)
                .toList();
        List<RevealedCubes> allMinimumCubes = allRevealedCubes.stream().map(this::getMinimumCubesOfGame).toList();
        return String.valueOf(multiplyAllCubesTogetherAndSum(allMinimumCubes));
    }

    private int multiplyAllCubesTogetherAndSum(List<RevealedCubes> allMinimumCubes) {

        return allMinimumCubes
                .stream()
                .map(cubes -> Math.max(1, cubes.red()) * Math.max(1, cubes.green()) * Math.max(1, cubes.blue()))
                .reduce(0, (a, b) -> a + b);
    }

    private RevealedCubes getMinimumCubesOfGame(List<RevealedCubes> allRevealedCubes) {
        RevealedCubes minimumCubes = allRevealedCubes.get(0);
        for (int i = 1; i < allRevealedCubes.size(); i++) {
            RevealedCubes revealedCubes = allRevealedCubes.get(i);
            minimumCubes = minimumCubes.getSmallestOfBothRevealedCubes(revealedCubes);
        }
        return minimumCubes;
    }

    private int sumPossibleGameIds(RevealedCubes requirement) {
        List<Game> allGames = convertAllLinesToGameList();
        List<Game> possibleGames = allGames
                .stream()
                .filter(game -> isGamePossible(game, requirement))
                .toList();
        return possibleGames.stream().map(Game::id).reduce(0, (a, b) -> a + b);
    }

    private boolean isGamePossible(Game game, RevealedCubes requirement) {
        for (RevealedCubes revealedCubes : game.revealedCubes()) {
            if (revealedCubes.red() > requirement.red()
                    || revealedCubes.green() > requirement.green()
                    || revealedCubes.blue() > requirement.blue()) {
                return false;
            }
        }
        return true;
    }

    private List<Game> convertAllLinesToGameList() {
        return allLines
                .stream()
                .map(this::convertLineIntoGame)
                .toList();
    }

    private Game convertLineIntoGame(String line) {
        Pattern idRegex = Pattern.compile("(\\d+)");
        Matcher idMatcher = idRegex.matcher(line);
        idMatcher.find();
        int id = Integer.parseInt(idMatcher.group(0));

        String[] reveals = line.substring(line.indexOf(":") + 2).split(";");
        List<RevealedCubes> revealedCubes = new ArrayList<>();

        for (String reveal : reveals) {
            int red = getCountOfColor(reveal, "red");
            int green = getCountOfColor(reveal, "green");
            int blue = getCountOfColor(reveal, "blue");

            revealedCubes.add(new RevealedCubes(red, green, blue));
        }

        return new Game(id, revealedCubes);
    }

    private int getCountOfColor(String str, String color) {
        Pattern colorNumberRegex = Pattern.compile("\\d+(?=\\s" + color + ")");
        Matcher colorNumberMatcher = colorNumberRegex.matcher(str);
        return colorNumberMatcher.find() ? Integer.parseInt(colorNumberMatcher.group(0)) : 0;
    }
}

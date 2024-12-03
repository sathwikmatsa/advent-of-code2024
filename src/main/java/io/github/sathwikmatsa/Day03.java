package io.github.sathwikmatsa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day03 {

    static String parseInput(String filename) throws IOException {
        return Files.readString(Paths.get(filename));
    }

    static String[] parseMulInstructions(String input) {
        return Pattern.compile("mul\\(\\d+,\\d+\\)").matcher(input).results().map(MatchResult::group)
                .toArray(String[]::new);
    }

    static String[] parseMulDoDontInstructions(String input) {
        return Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)").matcher(input).results()
                .map(MatchResult::group)
                .toArray(String[]::new);
    }

    static Integer execMulInstruction(String mul) {
        String[] parts = mul.split(",");
        return Integer.parseInt(parts[0].substring(4)) * Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
    }

    static Integer execMulsWithConditionals(String[] instructions) {
        boolean enabled = true;
        Integer res = 0;
        for (int i = 0; i < instructions.length; i++) {
            String ins = instructions[i];
            if (ins.equals("do()")) {
                enabled = true;
            } else if (ins.equals("don't()")) {
                enabled = false;
            } else {
                if (enabled) {
                    res += execMulInstruction(ins);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        String input = parseInput("input/day03_part1.txt");
        String[] muls = parseMulInstructions(input);
        System.out.println("part1: " + Stream.of(muls).mapToInt(Day03::execMulInstruction).sum());

        String[] mulsWithConditionals = parseMulDoDontInstructions(input);
        System.out.println("part2: " + execMulsWithConditionals(mulsWithConditionals));
    }
}

package io.github.sathwikmatsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

record ListPair(List<Integer> left, List<Integer> right) {
}

class Day01 {

    static ListPair parseInput(String filename) throws IOException {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = reader.readLine()) != null;) {
                String[] parts = line.split("\\s+");
                Integer l = Integer.parseInt(parts[0]);
                Integer r = Integer.parseInt(parts[1]);
                left.add(l);
                right.add(r);
            }
        }

        return new ListPair(left, right);
    }

    static int listPairDistance(ListPair lists) {
        List<Integer> left = lists.left();
        List<Integer> right = lists.right();
        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        return IntStream.range(0, left.size()).map(i -> Math.abs(left.get(i) - right.get(i))).sum();
    }

    static int listPairSimilarityScore(ListPair lists) {
        Map<Integer, Long> frequencyMap = lists.right().stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return lists.left().stream()
                .mapToInt(i -> i * frequencyMap.getOrDefault(i, 0L).intValue())
                .sum();
    }

    public static void main(String[] args) throws IOException {
        ListPair lists = parseInput("input/day01_part1.txt");
        System.out.println("Part1: " + listPairDistance(lists));
        System.out.println("Part2: " + listPairSimilarityScore(lists));
    }
}

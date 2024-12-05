package io.github.sathwikmatsa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.sathwikmatsa.utils.ListUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;

record OrderingRule(Integer before, Integer after) {
    public OrderingRule {
        if (before == after) {
            throw new IllegalArgumentException("before and after must be different");
        }
    }
}

record Update(Map<Integer, Integer> pageIndexMap, Integer middleElement) {
    public static Update from(List<Integer> pageNumbers) {
        return new Update(ListUtils.listToIndexMap(pageNumbers), pageNumbers.get(pageNumbers.size() / 2));
    }

    boolean satisfiesRule(OrderingRule rule) {
        if (pageIndexMap.containsKey(rule.before()) && pageIndexMap.containsKey(rule.after())) {
            return pageIndexMap.get(rule.before()) < pageIndexMap.get(rule.after());
        }
        return true;
    }
}

class Day05 {
    static SimpleEntry<List<OrderingRule>, List<Update>> parseInput(String filename) throws IOException {
        List<OrderingRule> rules = new ArrayList<>();
        List<Update> updates = new ArrayList<>();

        boolean parseRules = true;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = reader.readLine()) != null;) {
                if (line.length() == 0) {
                    parseRules = false;
                    continue;
                }

                if (parseRules) {
                    String[] parts = line.split("\\|");
                    rules.add(new OrderingRule(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
                } else {
                    String[] parts = line.split(",");
                    List<Integer> pageNumbers = Stream.of(parts).map(Integer::parseInt).collect(Collectors.toList());
                    updates.add(Update.from(pageNumbers));
                }
            }
        }
        return new SimpleEntry<>(rules, updates);
    }

    public static void main(String[] args) throws IOException {
        SimpleEntry<List<OrderingRule>, List<Update>> input = Day05.parseInput("input/day05_part1.txt");
        List<OrderingRule> rules = input.getKey();
        List<Update> updates = input.getValue();

        Integer middlePageSum = updates.stream()
                .filter(update -> rules.stream().allMatch(rule -> update.satisfiesRule(rule)))
                .mapToInt(update -> update.middleElement()).sum();

        System.out.println("part1: " + middlePageSum);
    }
}

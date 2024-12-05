package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Day05Test {
    @Test
    public void parseInput() throws IOException {
        SimpleEntry<List<OrderingRule>, List<Update>> input = Day05.parseInput("input/day05_test_part1.txt");
        List<OrderingRule> rules = input.getKey();
        List<Update> updates = input.getValue();

        assertEquals(21, rules.size());
        assertEquals(6, updates.size());

        assertEquals(new OrderingRule(47, 53), rules.getFirst());
        assertEquals(Update.from(List.of(75, 47, 61, 53, 29)), updates.getFirst());

        assertEquals(new OrderingRule(53, 13), rules.getLast());
        assertEquals(Update.from(List.of(97, 13, 75, 29, 47)), updates.getLast());
    }

    @Test
    public void updateSatisfiesRule() {
        Update update = Update.from(List.of(75, 47, 61, 53, 29));
        assertTrue(update.satisfiesRule(new OrderingRule(75, 47)));
        assertTrue(update.satisfiesRule(new OrderingRule(75, 61)));
        assertTrue(update.satisfiesRule(new OrderingRule(75, 53)));
        assertTrue(update.satisfiesRule(new OrderingRule(75, 29)));
        assertTrue(update.satisfiesRule(new OrderingRule(47, 61)));
        assertTrue(update.satisfiesRule(new OrderingRule(47, 53)));
        assertTrue(update.satisfiesRule(new OrderingRule(47, 29)));
        assertTrue(update.satisfiesRule(new OrderingRule(61, 53)));
        assertTrue(update.satisfiesRule(new OrderingRule(61, 29)));
        assertTrue(update.satisfiesRule(new OrderingRule(53, 29)));

        assertTrue(update.satisfiesRule(new OrderingRule(97, 13)));
        assertFalse(update.satisfiesRule(new OrderingRule(47, 75)));
    }

    @Test
    public void topologicalSort() throws IOException {
        List<OrderingRule> rules = Day05.parseInput("input/day05_test_part1.txt").getKey();
        Update update1 = Update.from(List.of(75, 97, 47, 61, 53));
        Update update2 = Update.from(List.of(61, 13, 29));
        Update update3 = Update.from(List.of(97, 13, 75, 29, 47));
        assertEquals(List.of(97, 75, 47, 61, 53), Day05.topologicalSort(update1, rules));
        assertEquals(List.of(61, 29, 13), Day05.topologicalSort(update2, rules));
        assertEquals(List.of(97, 75, 47, 29, 13), Day05.topologicalSort(update3, rules));
    }
}

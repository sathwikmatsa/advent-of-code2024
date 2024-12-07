package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class Day07Test {
    @Test
    public void parseInput() throws IOException {
        Map<Long, List<Long>> res = Day07.parseInput("input/day07_test_part1.txt");
        assertEquals(9, res.size());
        assertEquals(List.of(10L, 19L), res.get(190L));
    }

    @Test
    public void canProduceTestValue() {
        assertTrue(Day07.canProduceTestValue(List.of(10L, 19L), 190L));
        assertTrue(Day07.canProduceTestValue(List.of(81L, 40L, 27L), 3267L));
        assertTrue(Day07.canProduceTestValue(List.of(11L, 6L, 16L, 20L), 292L));

        assertFalse(Day07.canProduceTestValue(List.of(17L, 5L), 83L));
        assertFalse(Day07.canProduceTestValue(List.of(15L, 6L), 156L));
        assertFalse(Day07.canProduceTestValue(List.of(6L, 8L, 6L, 15L), 7290L));
        assertFalse(Day07.canProduceTestValue(List.of(16L, 10L, 13L), 161011L));
        assertFalse(Day07.canProduceTestValue(List.of(17L, 8L, 14L), 192L));
        assertFalse(Day07.canProduceTestValue(List.of(9L, 7L, 18L, 13L), 21037L));
    }

    @Test
    public void concatFn() {
        assertEquals(156L, Day07.CONCAT_FN.apply(15L, 6L));
    }

    @Test
    public void canProduceTestValueV2() {
        assertTrue(Day07.canProduceTestValueV2(List.of(10L, 19L), 190L));
        assertTrue(Day07.canProduceTestValueV2(List.of(81L, 40L, 27L), 3267L));
        assertTrue(Day07.canProduceTestValueV2(List.of(11L, 6L, 16L, 20L), 292L));
        assertTrue(Day07.canProduceTestValueV2(List.of(15L, 6L), 156L));
        assertTrue(Day07.canProduceTestValueV2(List.of(6L, 8L, 6L, 15L), 7290L));
        assertTrue(Day07.canProduceTestValueV2(List.of(17L, 8L, 14L), 192L));

        assertFalse(Day07.canProduceTestValueV2(List.of(17L, 5L), 83L));
        assertFalse(Day07.canProduceTestValueV2(List.of(16L, 10L, 13L), 161011L));
        assertFalse(Day07.canProduceTestValueV2(List.of(9L, 7L, 18L, 13L), 21037L));
    }

}

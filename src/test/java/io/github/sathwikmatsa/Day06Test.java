package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class Day06Test {
    @Test
    public void parseInput() throws IOException {
        assertEquals(10, Day06.parseInput("input/day06_test_part1.txt").length);
    }

    @Test
    public void directionRight() {
        assertEquals(Direction.RIGHT, Direction.UP.getRight());
        assertEquals(Direction.DOWN, Direction.RIGHT.getRight());
        assertEquals(Direction.LEFT, Direction.DOWN.getRight());
        assertEquals(Direction.UP, Direction.LEFT.getRight());
    }

    @Test
    public void patrolPositions() throws IOException {
        String[] input = Day06.parseInput("input/day06_test_part1.txt");
        assertEquals(41, Day06.patrolPositions(input));
    }
}

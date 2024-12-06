package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class Day06Test {
    @Test
    public void parseInput() throws IOException {
        SimpleEntry<GuardPosition, Character[][]> input = Day06.parseInput("input/day06_test_part1.txt");
        GuardPosition guard = input.getKey();
        Character[][] area = input.getValue();
        assertEquals(10, area.length);
        assertEquals(new GuardPosition(6, 4, Direction.UP), guard);
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
        SimpleEntry<GuardPosition, Character[][]> input = Day06.parseInput("input/day06_test_part1.txt");
        GuardPosition guard = input.getKey();
        Character[][] area = input.getValue();
        assertEquals(41, Day06.patrolPositions(area, guard).size());
    }

    @Test
    public void placeObstacleAndCheckLoop() throws IOException {
        SimpleEntry<GuardPosition, Character[][]> input = Day06.parseInput("input/day06_test_part1.txt");
        GuardPosition guard = input.getKey();
        Character[][] area = input.getValue();

        assertTrue(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(6, 3)));
        assertTrue(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(7, 6)));
        assertTrue(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(7, 7)));
        assertTrue(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(8, 1)));
        assertTrue(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(8, 3)));
        assertTrue(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(9, 7)));

        assertFalse(Day06.placeObstacleAndCheckLoop(area, guard, new SimpleEntry<Integer, Integer>(0, 0)));
    }

    @Test
    public void loopingObstructions() throws IOException {
        SimpleEntry<GuardPosition, Character[][]> input = Day06.parseInput("input/day06_test_part1.txt");
        GuardPosition guard = input.getKey();
        Character[][] area = input.getValue();

        Set<SimpleEntry<Integer, Integer>> positions = Day06.patrolPositions(area, guard.clone());

        assertEquals(6, Day06.loopingObstructions(area, guard, positions));

    }
}

package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.github.sathwikmatsa.utils.ListUtils;
import io.github.sathwikmatsa.utils.Pair;

public class Day08Test {
    @Test
    public void parseInput() throws IOException {
        Pair<Collection<List<GridPos>>, Pair<Integer, Integer>> input = Day08
                .parseInput("input/day08_test_part1.txt");
        Collection<List<GridPos>> antennas = input.first();
        Pair<Integer, Integer> dimensions = input.second();
        assertEquals(2, antennas.size());
        assertEquals(7, antennas.stream().mapToInt(List::size).sum());
        assertEquals(new Pair<>(12, 12), dimensions);
    }

    @Test
    public void antinodes() {
        assertEquals(new Pair<>(new GridPos(1, 3), new GridPos(7, 6)),
                Day08.antinodes(new Pair<>(new GridPos(3, 4), new GridPos(5, 5))));
    }

    @Test
    public void antinodesV2() {
        assertEquals(List.of(new GridPos(2, 6), new GridPos(3, 9), new GridPos(0, 0), new GridPos(1, 3)),
                Day08.antinodesV2(new Pair<>(new GridPos(0, 0), new GridPos(1, 3)), new Pair<>(10, 10)));
    }

    @Test
    public void countAntinodesV2() throws IOException {
        Pair<Collection<List<GridPos>>, Pair<Integer, Integer>> input = Day08.parseInput("input/day08_test_part1.txt");
        Collection<List<GridPos>> antennas = input.first();
        Pair<Integer, Integer> dimensions = input.second();

        long uniqueAntinodesV2 = antennas.stream().flatMap(list -> ListUtils.pairwiseCombinations(list))
                .flatMap(p -> Day08.antinodesV2(p, dimensions).stream()).distinct().count();
        assertEquals(34, uniqueAntinodesV2);
    }
}

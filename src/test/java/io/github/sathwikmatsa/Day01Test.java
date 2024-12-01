package io.github.sathwikmatsa;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Day01Test {
    @Test
    public void parseInput() throws IOException {
        ListPair lists = Day01.parseInput("input/day01_test_listpair.txt");
        assertEquals(Arrays.asList(3, 4, 2, 1, 3, 3), lists.left());
        assertEquals(Arrays.asList(4, 3, 5, 3, 9, 3), lists.right());
    }

    @Test
    public void listPairDistance() {
        List<Integer> left = Arrays.asList(3, 4, 2, 1, 3, 3);
        List<Integer> right = Arrays.asList(4, 3, 5, 3, 9, 3);
        assertEquals(11, Day01.listPairDistance(new ListPair(left, right)));
    }

}
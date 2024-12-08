package io.github.sathwikmatsa.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class ListUtilsTest {
    @Test
    public void pairwiseCombinations() {
        assertEquals(List.of(new Pair<>(1, 2), new Pair<>(1, 3), new Pair<>(2, 3)),
                ListUtils.pairwiseCombinations(List.of(1, 2, 3)).collect(Collectors.toList()));
    }
}

package io.github.sathwikmatsa.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ListUtils {
    public static <T> Map<T, Integer> listToIndexMap(List<T> list) {
        return IntStream.range(0, list.size()).boxed().collect(Collectors.toMap(list::get, i -> i));
    }

    public static <T> Stream<Pair<T, T>> pairwiseCombinations(List<T> list) {
        return IntStream.range(0, list.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, list.size()).mapToObj(j -> new Pair<>(list.get(i), list.get(j))));
    }
}

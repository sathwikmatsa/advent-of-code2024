package io.github.sathwikmatsa.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListUtils {
    public static <T> Map<T, Integer> listToIndexMap(List<T> list) {
        return IntStream.range(0, list.size()).boxed().collect(Collectors.toMap(list::get, i -> i));
    }
}

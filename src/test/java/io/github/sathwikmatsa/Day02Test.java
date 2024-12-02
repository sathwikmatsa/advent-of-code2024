package io.github.sathwikmatsa;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Day02Test {
    @Test
    public void parseInput() throws IOException {
        List<List<Integer>> reports = Day02.parseInput("input/day02_test_levels.txt");
        assertEquals(6, reports.size());
        assertEquals(Arrays.asList(7, 6, 4, 2, 1), reports.get(0));
        assertEquals(Arrays.asList(1, 3, 6, 7, 9), reports.get(5));
    }

    @Test
    public void isReportSafe() {
        assertEquals(true, Day02.isReportSafe(Arrays.asList(7, 6, 4, 2, 1)));
        assertEquals(false, Day02.isReportSafe(Arrays.asList(1, 2, 7, 8, 9)));
        assertEquals(false, Day02.isReportSafe(Arrays.asList(9, 7, 6, 2, 1)));
        assertEquals(false, Day02.isReportSafe(Arrays.asList(1, 3, 2, 4, 5)));
        assertEquals(false, Day02.isReportSafe(Arrays.asList(8, 6, 4, 4, 1)));
        assertEquals(true, Day02.isReportSafe(Arrays.asList(1, 3, 6, 7, 9)));

        assertEquals(false, Day02.isReportSafe(Arrays.asList(8, 8, 7, 6, 5)));
        assertEquals(false, Day02.isReportSafe(Arrays.asList(8, 8, 9, 10, 11)));
        assertEquals(false, Day02.isReportSafe(Arrays.asList(8, 8)));
    }

    @Test
    public void isReportSafeV2() {
        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(7, 6, 4, 2, 1)));
        assertEquals(false, Day02.isReportSafeV2(Arrays.asList(1, 2, 7, 8, 9)));
        assertEquals(false, Day02.isReportSafeV2(Arrays.asList(9, 7, 6, 2, 1)));
        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(1, 3, 2, 4, 5)));
        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(8, 6, 4, 4, 1)));
        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(1, 3, 6, 7, 9)));

        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(9, 8, 9, 10)));
        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(7, 9, 8, 9, 10)));
        assertEquals(true, Day02.isReportSafeV2(Arrays.asList(9, 10, 8, 7)));

    }
}

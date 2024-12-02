package io.github.sathwikmatsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Day02 {

    static List<List<Integer>> parseInput(String filename) throws IOException {
        List<List<Integer>> res = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = reader.readLine()) != null;) {
                String[] parts = line.split("\\s+");
                List<Integer> levels = new ArrayList<>();
                for (int i = 0; i < parts.length; i++) {
                    levels.add(Integer.parseInt(parts[i]));
                }
                res.add(levels);
            }
        }

        return res;
    }

    static boolean isReportSafe(List<Integer> report) {
        assert (report.size() > 1);
        boolean increasing = report.get(0) < report.get(1);
        return IntStream.range(1, report.size()).allMatch(i -> {
            Integer left = report.get(i - 1);
            Integer right = report.get(i);
            int diff = Math.abs(left - right);
            return (increasing ? right > left : right < left) && diff >= 1 && diff <= 3;
        });
    }

    static long countSafeReports(List<List<Integer>> reports) {
        return reports.stream().filter(Day02::isReportSafe).count();
    }

    static boolean isReportSafeV2(List<Integer> report) {
        if (isReportSafe(report)) {
            return true;
        }

        boolean increasing = report.get(0) < report.get(1);
        int fault_index = IntStream.range(1, report.size()).filter(i -> {
            Integer left = report.get(i - 1);
            Integer right = report.get(i);
            int diff = Math.abs(left - right);
            boolean safe = (increasing ? right > left : right < left) && diff >= 1 && diff <= 3;
            return !safe;
        }).findFirst().getAsInt();

        List<Integer> report_without_fi = IntStream.range(0, report.size()).filter(i -> i != fault_index)
                .mapToObj(report::get).collect(Collectors.toList());
        List<Integer> report_without_fip = IntStream.range(0, report.size()).filter(i -> i != fault_index - 1)
                .mapToObj(report::get).collect(Collectors.toList());
        List<Integer> report_without_first = IntStream.range(0, report.size()).filter(i -> i != 0)
                .mapToObj(report::get).collect(Collectors.toList());
        return isReportSafe(report_without_fi) || isReportSafe(report_without_fip)
                || isReportSafe(report_without_first);
    }

    static long countSafeReportsV2(List<List<Integer>> reports) {
        return reports.stream().filter(Day02::isReportSafeV2).count();
    }

    public static void main(String[] args) throws IOException {
        List<List<Integer>> reports = parseInput("input/day02_part1.txt");
        System.out.println("Part1: " + countSafeReports(reports));
        System.out.println("Part2: " + countSafeReportsV2(reports));
    }
}

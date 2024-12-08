package io.github.sathwikmatsa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.github.sathwikmatsa.utils.ListUtils;
import io.github.sathwikmatsa.utils.Pair;

record GridPos(int row, int col) {
    public boolean isValid(Pair<Integer, Integer> dimensions) {
        int nrows = dimensions.first();
        int ncols = dimensions.second();
        return row >= 0 && row < nrows && col >= 0 && col < ncols;
    }
}

class Day08 {
    static Pair<Collection<List<GridPos>>, Pair<Integer, Integer>> parseInput(String filename) throws IOException {
        Map<Character, List<GridPos>> res = new HashMap<>();
        int row = 0;
        int ncols = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (String line; (line = reader.readLine()) != null;) {
                ncols = line.length();
                final String fline = line;
                final int frow = row;
                IntStream.range(0, line.length()).filter(i -> fline.charAt(i) != '.')
                        .forEach(col -> {
                            char ch = fline.charAt(col);
                            if (!res.containsKey(ch)) {
                                res.put(ch, new ArrayList<GridPos>());
                            }
                            res.get(ch).add(new GridPos(frow, col));
                        });
                ;
                row += 1;
            }
        }
        return new Pair<>(res.values(), new Pair<>(row, ncols));
    }

    static Pair<GridPos, GridPos> antinodes(Pair<GridPos, GridPos> antennas) {
        GridPos a1 = antennas.first();
        GridPos a2 = antennas.second();
        int rowdiff = a1.row() - a2.row();
        int coldiff = a1.col() - a2.col();
        return new Pair<>(new GridPos(a1.row() + rowdiff, a1.col() + coldiff),
                new GridPos(a2.row() - rowdiff, a2.col() - coldiff));
    }

    static List<GridPos> antinodesV2(Pair<GridPos, GridPos> antennas, Pair<Integer, Integer> dimPair) {
        GridPos a1 = antennas.first();
        GridPos a2 = antennas.second();
        int rowdiff = a1.row() - a2.row();
        int coldiff = a1.col() - a2.col();

        int nrows = dimPair.first();
        int ncols = dimPair.second();

        List<GridPos> res = new ArrayList<>();

        int a1row = a1.row() + rowdiff;
        int a1col = a1.col() + coldiff;

        while (a1row >= 0 && a1row < nrows && a1col >= 0 && a1col < ncols) {
            res.add(new GridPos(a1row, a1col));
            a1row += rowdiff;
            a1col += coldiff;
        }

        int a2row = a2.row() - rowdiff;
        int a2col = a2.col() - coldiff;

        while (a2row >= 0 && a2row < nrows && a2col >= 0 && a2col < ncols) {
            res.add(new GridPos(a2row, a2col));
            a2row -= rowdiff;
            a2col -= coldiff;
        }

        res.add(a1);
        res.add(a2);

        return res;
    }

    public static void main(String[] args) throws IOException {
        Pair<Collection<List<GridPos>>, Pair<Integer, Integer>> input = parseInput("input/day08_part1.txt");
        Collection<List<GridPos>> antennas = input.first();
        Pair<Integer, Integer> dimensions = input.second();

        long uniqueAntinodes = antennas.stream().flatMap(list -> ListUtils.pairwiseCombinations(list))
                .map(Day08::antinodes)
                .flatMap(p -> Stream.of(p.first(), p.second()))
                .filter(pos -> pos.isValid(dimensions)).distinct().count();
        System.out.println("part1: " + uniqueAntinodes);

        long uniqueAntinodesV2 = antennas.stream().flatMap(list -> ListUtils.pairwiseCombinations(list))
                .flatMap(p -> Day08.antinodesV2(p, dimensions).stream()).distinct().count();
        System.out.println("part2: " + uniqueAntinodesV2);
    }
}

package io.github.sathwikmatsa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.Set;
import java.util.stream.Collectors;

record Grid(List<String> lines) {
    public Grid

    {
        if (lines.size() == 0) {
            throw new IllegalArgumentException("lines cannot be empty");
        }
        if (lines.stream().anyMatch(s -> s.length() != lines.get(0).length())) {
            throw new IllegalArgumentException("all strings must be of same length");
        }
    }

    public static Grid fromFile(String filename) throws IOException {
        return new Grid(Files.readAllLines(Paths.get(filename)));
    }

    public static Grid of(String content) {
        return new Grid(content.lines().collect(Collectors.toList()));
    }

    public char get(int r, int c) {
        return lines.get(r).charAt(c);
    }

    public int nrows() {
        return lines.size();
    }

    public int ncols() {
        return lines.get(0).length();
    }

    public boolean isValidPos(int r, int c) {
        return r >= 0 && r < nrows() && c >= 0 && c < ncols();
    }

}

class Day04 {

    private static final char[] LINEAR_SEARCH = { 'X', 'M', 'A', 'S' };
    private static final List<int[]> DIRECTIONS = List.of(
            new int[] { 0, 1 },
            new int[] { 0, -1 },
            new int[] { 1, 0 },
            new int[] { -1, 0 },
            new int[] { 1, 1 },
            new int[] { 1, -1 },
            new int[] { -1, 1 },
            new int[] { -1, -1 });

    private static final Set<Character> X_SEARCH = Set.of('M', 'S');

    private static final List<int[]> LEFT_DIAGONAL = List.of(
            new int[] { 1, 1 },
            new int[] { -1, -1 });

    private static final List<int[]> RIGHT_DIAGONAL = List.of(
            new int[] { -1, 1 },
            new int[] { 1, -1 });

    static boolean searchLinear(Grid grid, int r, int c, int[] direction) {
        for (int i = 0; i < LINEAR_SEARCH.length; i++) {
            int nr = r + direction[0] * i;
            int nc = c + direction[1] * i;

            if (!grid.isValidPos(nr, nc) || !(grid.get(nr, nc) == LINEAR_SEARCH[i])) {
                return false;
            }
        }
        return true;
    }

    static boolean searchX(Grid grid, int r, int c) {
        if (grid.get(r, c) == 'A') {
            Function<List<int[]>, Boolean> checkDiagonal = directions -> directions.stream()
                    .filter(d -> grid.isValidPos(r + d[0], c + d[1]))
                    .map(d -> grid.get(r + d[0], c + d[1])).collect(Collectors.toSet()).equals(X_SEARCH);
            return checkDiagonal.apply(LEFT_DIAGONAL) && checkDiagonal.apply(RIGHT_DIAGONAL);
        }
        return false;
    }

    static int searchXMAS(Grid grid) {
        int nrows = grid.nrows();
        int ncols = grid.ncols();

        int count = 0;
        for (int r = 0; r < nrows; r++) {
            for (int c = 0; c < ncols; c++) {
                final int row = r;
                final int col = c;
                count += DIRECTIONS.stream().filter(direction -> searchLinear(grid, row, col, direction)).count();
            }
        }

        return count;
    }

    static int searchX_MAS(Grid grid) {
        int nrows = grid.nrows();
        int ncols = grid.ncols();

        int count = 0;
        for (int r = 0; r < nrows; r++) {
            for (int c = 0; c < ncols; c++) {
                final int row = r;
                final int col = c;
                count = searchX(grid, row, col) ? count + 1 : count;
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        Grid grid = Grid.fromFile("input/day04_part1.txt");
        System.out.println("part1: " + Day04.searchXMAS(grid));
        System.out.println("part2: " + Day04.searchX_MAS(grid));
    }
}

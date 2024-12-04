package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Day04Test {
    @Test
    public void createGrid() throws IOException {
        // empty list
        assertThrows(IllegalArgumentException.class, () -> new Grid(List.of()));
        // rows of different length
        assertThrows(IllegalArgumentException.class, () -> new Grid(List.of("xx", "x")));

        Grid gridFromFile = Grid.fromFile("input/day04_test.txt");
        Grid gridFromStr = Grid.of("""
                MMMSXXMASM
                MSAMXMSMSA
                AMXSXMAAMM
                MSAMASMSMX
                XMASAMXAMM
                XXAMMXXAMA
                SMSMSASXSS
                SAXAMASAAA
                MAMMMXMMMM
                MXMXAXMASX
                """);

        assertEquals(10, gridFromFile.lines().size());
        assertEquals(gridFromFile.lines(), gridFromStr.lines());
    }

    @Test
    public void gridGet() throws IOException {
        Grid grid = Grid.fromFile("input/day04_test.txt");
        assertEquals('X', grid.get(4, 6));
        assertEquals('M', grid.get(0, 0));
        assertEquals('A', grid.get(1, 9));
        assertEquals('S', grid.get(9, 8));
    }

    @Test
    public void gridValidPos() throws IOException {
        Grid grid = Grid.fromFile("input/day04_test.txt");
        assertEquals(10, grid.nrows());
        assertEquals(10, grid.ncols());
        assertTrue(grid.isValidPos(0, 0));
        assertTrue(grid.isValidPos(9, 9));
        assertTrue(grid.isValidPos(5, 4));
        assertFalse(grid.isValidPos(-1, 4));
        assertFalse(grid.isValidPos(9, 10));
    }

    @Test
    public void searchTowards() {
        String content = """
                ..X...
                .SAMX.
                .A..A.
                XMAS.S
                .X....
                """;
        Grid grid = Grid.of(content);
        assertTrue(Day04.searchTowards(grid, 0, 2, new int[] { 1, 1 }));
        assertTrue(Day04.searchTowards(grid, 1, 4, new int[] { 0, -1 }));
        assertTrue(Day04.searchTowards(grid, 3, 0, new int[] { 0, 1 }));
        assertTrue(Day04.searchTowards(grid, 4, 1, new int[] { -1, 0 }));
        assertFalse(Day04.searchTowards(grid, 0, 2, new int[] { 0, 1 }));
    }

    @Test
    public void searchGrid() throws IOException {
        Grid grid = Grid.fromFile("input/day04_test.txt");
        assertEquals(18, Day04.searchGrid(grid));
    }
}

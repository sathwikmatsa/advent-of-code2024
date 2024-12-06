package io.github.sathwikmatsa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

enum Direction {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    private final int dr;
    private final int dc;

    private Direction(int dr, int dc) {
        this.dr = dr;
        this.dc = dc;
    }

    public int getDr() {
        return dr;
    }

    public int getDc() {
        return dc;
    }

    public Direction getRight() {
        return values()[(this.ordinal() + 1) % values().length];
    }
}

class GuardPosition {
    public GuardPosition(int row, int col, Direction direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public SimpleEntry<Integer, Integer> getPos() {
        return new SimpleEntry<Integer, Integer>(row, col);
    }

    public boolean advance(String[] patrolArea) {
        while (true) {
            int nr = row + direction.getDr();
            int nc = col + direction.getDc();

            if (nr < patrolArea.length && nc < patrolArea[0].length()) {
                if (patrolArea[nr].charAt(nc) == '#') {
                    direction = direction.getRight();
                } else {
                    this.row = nr;
                    this.col = nc;
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private int row;
    private int col;
    private Direction direction;
}

class Day06 {
    static String[] parseInput(String filename) throws IOException {
        return Files.readString(Paths.get(filename)).split("\n");
    }

    static int patrolPositions(String[] area) {
        Set<SimpleEntry<Integer, Integer>> posSet = new HashSet<>();
        GuardPosition guard = IntStream.range(0, area.length).filter(r -> area[r].contains("^"))
                .mapToObj(r -> new GuardPosition(r, area[r].indexOf('^'), Direction.UP)).findFirst().get();
        posSet.add(guard.getPos());
        while (guard.advance(area)) {
            posSet.add(guard.getPos());
        }
        return posSet.size();
    }

    public static void main(String[] args) throws IOException {
        String[] input = parseInput("input/day06_part1.txt");
        System.out.println("part1: " + patrolPositions(input));
    }
}

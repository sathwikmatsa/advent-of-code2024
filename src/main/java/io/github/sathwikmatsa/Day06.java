package io.github.sathwikmatsa;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

class GuardPosition implements Cloneable {
    public GuardPosition(int row, int col, Direction direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public SimpleEntry<Integer, Integer> getPos() {
        return new SimpleEntry<Integer, Integer>(row, col);
    }

    public boolean advance(Character[][] patrolArea) {
        while (true) {
            int nr = row + direction.getDr();
            int nc = col + direction.getDc();

            if (nr >= 0 && nr < patrolArea.length && nc >= 0 && nc < patrolArea[0].length) {
                if (patrolArea[nr][nc] == '#') {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Integer.hashCode(row);
        result = prime * result + Integer.hashCode(col);
        result = prime * result + direction.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GuardPosition))
            return false;
        GuardPosition other = (GuardPosition) obj;
        return row == other.row && col == other.col && direction == other.direction;
    }

    @Override
    public GuardPosition clone() {
        try {
            return (GuardPosition) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

class Day06 {
    static SimpleEntry<GuardPosition, Character[][]> parseInput(String filename) throws IOException {
        Character[][] area = Stream.of(Files.readString(Paths.get(filename)).split("\n"))
                .map(line -> line.chars().mapToObj(c -> (char) c).toArray(Character[]::new))
                .toArray(Character[][]::new);
        GuardPosition guard = IntStream.range(0, area.length)
                .filter(r -> Arrays.stream(area[r]).anyMatch(c -> c == '^'))
                .mapToObj(r -> new GuardPosition(r, Arrays.asList(area[r]).indexOf('^'), Direction.UP)).findFirst()
                .get();
        return new SimpleEntry<>(guard, area);
    }

    static Set<SimpleEntry<Integer, Integer>> patrolPositions(Character[][] area, GuardPosition guardOrig) {
        GuardPosition guard = guardOrig.clone();
        Set<SimpleEntry<Integer, Integer>> posSet = new HashSet<>();
        posSet.add(guard.getPos());
        while (guard.advance(area)) {
            posSet.add(guard.getPos());
        }
        return posSet;
    }

    static boolean placeObstacleAndCheckLoop(Character[][] area, GuardPosition guardOrig,
            SimpleEntry<Integer, Integer> obstaclePos) {
        area[obstaclePos.getKey()][obstaclePos.getValue()] = '#';
        Set<GuardPosition> posSet = new HashSet<>();
        GuardPosition guard = guardOrig.clone();
        boolean loop = false;
        while (guard.advance(area)) {
            if (posSet.contains(guard)) {
                loop = true;
                break;
            }
            posSet.add(guard.clone());
        }
        area[obstaclePos.getKey()][obstaclePos.getValue()] = '.';
        return loop;
    }

    static long loopingObstructions(Character[][] area, GuardPosition guard,
            Set<SimpleEntry<Integer, Integer>> allowList) {
        return allowList.stream().filter(pos -> pos != guard.getPos() && placeObstacleAndCheckLoop(area, guard, pos))
                .count();
    }

    public static void main(String[] args) throws IOException {
        SimpleEntry<GuardPosition, Character[][]> input = parseInput("input/day06_part1.txt");
        GuardPosition guard = input.getKey();
        Character[][] area = input.getValue();

        Set<SimpleEntry<Integer, Integer>> positions = patrolPositions(area, guard);

        System.out.println("part1: " + positions.size());
        System.out.println("part2: " + loopingObstructions(area, guard, positions));
    }
}

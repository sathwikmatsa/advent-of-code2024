package io.github.sathwikmatsa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class Day03Test {
    @Test
    public void parseInput() throws IOException {
        assertEquals("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))",
                Day03.parseInput("input/day03_test_memory.txt"));
    }

    @Test
    public void parseMulInstructions() {
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
        assertTrue(Arrays.equals(new String[] { "mul(2,4)", "mul(5,5)", "mul(11,8)", "mul(8,5)" },
                Day03.parseMulInstructions(input)));
    }

    @Test
    public void execMulInstruction() {
        assertEquals(2 * 4, Day03.execMulInstruction("mul(2,4)"));
        assertEquals(5 * 5, Day03.execMulInstruction("mul(5,5)"));
        assertEquals(11 * 8, Day03.execMulInstruction("mul(11,8)"));
        assertEquals(8 * 5, Day03.execMulInstruction("mul(8,5)"));
    }
}

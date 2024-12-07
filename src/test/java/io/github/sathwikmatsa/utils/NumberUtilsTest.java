package io.github.sathwikmatsa.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberUtilsTest {
    @Test
    public void countDigits() {
        assertEquals(2, NumberUtils.countDigits(15));
        assertEquals(2, NumberUtils.countDigits(15L));
        assertEquals(1, NumberUtils.countDigits(0));
        assertEquals(5, NumberUtils.countDigits(-12345));
    }
}

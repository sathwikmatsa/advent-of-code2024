package io.github.sathwikmatsa.utils;

public class NumberUtils {
    public static <T extends Number> long countDigits(T number) {
        long n = number.longValue();
        return n == 0 ? 1 : (long) Math.log10(Math.abs(n)) + 1;
    }
}

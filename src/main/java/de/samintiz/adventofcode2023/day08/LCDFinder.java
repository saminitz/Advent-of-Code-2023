package de.samintiz.adventofcode2023.day08;

import java.util.List;

public class LCDFinder {

    private LCDFinder() {
    }

    private static long findGreatestCommonDivisor(long num1, long num2) {
        while (num2 != 0) {
            long temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return num1;
    }

    private static long findLeastCommonMultiply(long num1, long num2) {
        return num1 * (num2 / findGreatestCommonDivisor(num1, num2));
    }

    public static long findLeastCommonDenominator(List<Long> longs) {
        long leastCommonMultiply = longs.get(0);

        for (int i = 1; i < longs.size(); i++) {
            long currentNumber = longs.get(i);
            leastCommonMultiply = findLeastCommonMultiply(leastCommonMultiply, currentNumber);
        }

        return leastCommonMultiply;
    }
}

package org.project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Implmentation inspired by https://www.baeldung.com/java-convert-roman-arabic
 *
 */
public final class RomanConverter2 {
    public static final int CAPACITY = 1024;

    private RomanConverter2() {
        // prevent instantiation
    }

    private enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private final int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
              .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
              .collect(Collectors.toList());
        }
    }

    /**
     * Converts a roman numeral to an arabic number.
     *
     * @param romanNumber roman numeral to convert
     * @return arabic number
     * @throws NoSuchElementException if roman numeral is out of range
     */
    public static Integer fromRoman(String romanNumber) {
        String input = romanNumber.toUpperCase(Locale.ROOT);
        if (input.isEmpty() || !Utils.containsValidChars(input)) {
            throw new NoSuchElementException("Roman numerals must be" +
                    " a value from 1(I) - 3,999(MMMCMXCIX) inclusive.");
        }
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while (!input.isEmpty() && i < romanNumerals.size()) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (input.startsWith(symbol.name())) {
                result += symbol.getValue();
                input = input.substring(symbol.name().length());
            }
            else {
                i++;
            }
        }
        return result;
    }

    /**
     * Converts an arabic number to a roman numeral.
     *
     * @param arabicNumber number to convert
     * @return roman numeral
     * @throws NoSuchElementException if arabic number is out of range
     */
    public static String toRoman(Integer arabicNumber) {
        if (arabicNumber <= 0 || arabicNumber > Utils.MAX_ROMAN) {
            throw new NoSuchElementException("Arabic numerals must be between" +
                    " 1(I) - 3,999(MMMCMXCIX) inclusive to convert to Roman numerals.");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        int n = arabicNumber;
        StringBuilder sb = new StringBuilder(CAPACITY);

        while (n > 0 && i < romanNumerals.size()) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= n) {
                sb.append(currentSymbol.name());
                n -= currentSymbol.getValue();
            }
            else {
                i++;
            }
        }

        return sb.toString();
    }
}

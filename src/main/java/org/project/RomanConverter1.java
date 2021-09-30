package org.project;

import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Implementation inspired from https://stackoverflow.com/a/19759564/13160102
 * and https://java2blog.com/convert-roman-number-to-integer-java/
 */
public final class RomanConverter1 {

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
        final int n = Utils.INTEGER_TO_ROMAN.floorKey(arabicNumber);
        final String result;
        if (arabicNumber == n) {
            result = Utils.INTEGER_TO_ROMAN.get(arabicNumber);
        } else {
            result = Utils.INTEGER_TO_ROMAN.get(n) + toRoman(arabicNumber - n);
        }
        return result;
    }

    /**
     * Converts a roman numeral to an arabic number.
     *
     * @param romanNumber roman numeral to convert
     * @return arabic number
     * @throws NoSuchElementException if roman numeral is out of range
     */
    public static Integer fromRoman(String romanNumber) {
        final String input = romanNumber.toUpperCase(Locale.ROOT);
        if (input.isEmpty() || !containsValidChars(input)) {
            throw new NoSuchElementException("Roman numerals must be" +
                    " a value from 1(I) - 3,999(MMMCMXCIX) inclusive.");
        }

        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            Character ch = input.charAt(i);
            final boolean isUsingSubtractiveNotation = i > 0
                    && Utils.ROMAN_TO_INTEGER.get(ch)
                        > Utils.ROMAN_TO_INTEGER.get(input.charAt(i - 1));

            //Case 1
            if (isUsingSubtractiveNotation) {
                result += Utils.ROMAN_TO_INTEGER.get(ch) - 2
                        * Utils.ROMAN_TO_INTEGER.get(input.charAt(i - 1));
            }

            // Case 2: just add the corresponding number to result.
            else {
                result += Utils.ROMAN_TO_INTEGER.get(ch);
            }
        }

        return result;
    }

    private static boolean containsValidChars(String input) {
        boolean result = true;
        for (int i = 0; i < input.length(); i++) {
            if (!Utils.ROMAN_TO_INTEGER.containsKey(input.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
}

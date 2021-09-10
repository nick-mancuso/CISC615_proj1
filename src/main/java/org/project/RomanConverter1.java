package org.project;

import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Implemetation inspired from https://stackoverflow.com/a/19759564/13160102
 * and https://java2blog.com/convert-roman-number-to-integer-java/
 */
public final class RomanConverter1 {

    private static final TreeMap<Integer, String> INTEGER_TO_ROMAN = new TreeMap<>();
    private static final TreeMap<Character, Integer> ROMAN_TO_INTEGER;
    public static final int MAX_ROMAN = 3999;

    static {
        // init
        INTEGER_TO_ROMAN.put(1000, "M");
        INTEGER_TO_ROMAN.put(900, "CM");
        INTEGER_TO_ROMAN.put(500, "D");
        INTEGER_TO_ROMAN.put(400, "CD");
        INTEGER_TO_ROMAN.put(100, "C");
        INTEGER_TO_ROMAN.put(90, "XC");
        INTEGER_TO_ROMAN.put(50, "L");
        INTEGER_TO_ROMAN.put(40, "XL");
        INTEGER_TO_ROMAN.put(10, "X");
        INTEGER_TO_ROMAN.put(9, "IX");
        INTEGER_TO_ROMAN.put(5, "V");
        INTEGER_TO_ROMAN.put(4, "IV");
        INTEGER_TO_ROMAN.put(1, "I");

        ROMAN_TO_INTEGER = invertMap(INTEGER_TO_ROMAN);
    }

    private RomanConverter1() {
        // prevent instantiation
    }

    /**
     * Converts an arabic number to a roman numeral.
     *
     * @param arabicNumber number to convert
     * @return roman numeral
     * @throws NoSuchElementException if arabic number is out of range
     */
    public static String toRoman(Integer arabicNumber) {
        if (arabicNumber <= 0 || arabicNumber > MAX_ROMAN) {
            throw new NoSuchElementException("Arabic numerals must be between" +
                    " 1(I) - 3,999(MMMCMXCIX) to convert to Roman numerals.");
        }
        final int n = INTEGER_TO_ROMAN.floorKey(arabicNumber);
        final String result;
        if (arabicNumber == n) {
            result = INTEGER_TO_ROMAN.get(arabicNumber);
        } else {
            result = INTEGER_TO_ROMAN.get(n) + toRoman(arabicNumber - n);
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
                    " a value from 1(I) - 3,999(MMMCMXCIX)");
        }

        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            Character ch = input.charAt(i);
            final boolean isUsingSubtractiveNotation = i > 0
                    && ROMAN_TO_INTEGER.get(ch)
                        > ROMAN_TO_INTEGER.get(input.charAt(i - 1));

            //Case 1
            if (isUsingSubtractiveNotation) {
                result += ROMAN_TO_INTEGER.get(ch) - 2
                        * ROMAN_TO_INTEGER.get(input.charAt(i - 1));
            }

            // Case 2: just add the corresponding number to result.
            else {
                result += ROMAN_TO_INTEGER.get(ch);
            }
        }

        return result;
    }

    private static boolean containsValidChars(String input) {
        boolean result = true;
        for (int i = 0; i < input.length(); i++) {
            if (!ROMAN_TO_INTEGER.containsKey(input.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Inverts a given map by exchanging each entry's key and value.
     *
     * @param map source map
     * @return inverted map
     */
    private static TreeMap<Character, Integer> invertMap(SortedMap<Integer, String> map) {
        TreeMap<Character, Integer> newMap = new TreeMap<>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getValue().length() < 2) {
                newMap.put(entry.getValue().charAt(0), entry.getKey());
            }
        }
        return newMap;
    }
}

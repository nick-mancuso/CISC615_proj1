package org.project;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class FunctionalityBasedApproachTest {

    private static final List<String> ROMAN_NUMERALS_LIST
            = Arrays.asList(Utils.getRomanNumerals());

    /* Arabic */

    // c1 ... c10, b1
    @ParameterizedTest
    @ValueSource(ints = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void testContainsTrue(int input) {
        assertEquals(ROMAN_NUMERALS_LIST.get(input),
                RomanConverter1.toRoman(input));
    }

    // c1 ... c10, b2
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5, 6, 7, 8, 9, 2, 9})
    void testContainsFalse(int input) {
        assertEquals(ROMAN_NUMERALS_LIST.get(input),
                RomanConverter1.toRoman(input));
    }

    // c10b1 ... c1b4
    @ParameterizedTest
    @ValueSource(ints = {3, 14, 254, 3656})
    void testNumberOfPlaceValuesValid(int input) {
        assertEquals(ROMAN_NUMERALS_LIST.get(input),
                RomanConverter1.toRoman(input));
    }

    // c10b5 ... c1b10
    @ParameterizedTest
    @ValueSource(ints = {
            35656, 143455, 5675678, 12345678,
            123456789, 1234567897
    })
    void testNumberOfPlaceValuesInvalid(int input) {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.toRoman(input));
    }

    /* Roman */

    // c1b1
    @ParameterizedTest
    @MethodSource
    void testLetterCaseUpper(String input) {
        assertEquals(ROMAN_NUMERALS_LIST.indexOf(input),
                RomanConverter1.fromRoman(input));
    }

    private static Stream<String> testLetterCaseUpper() {
        return ROMAN_NUMERALS_LIST.stream()
                .skip(1);
    }

    // c1b2
    @ParameterizedTest
    @MethodSource
    void testLetterCaseLower(String input) {
        assertEquals(ROMAN_NUMERALS_LIST.indexOf(input.toUpperCase(Locale.ROOT)),
                RomanConverter1.fromRoman(input));
    }

    private static Stream<String> testLetterCaseLower() {
        return ROMAN_NUMERALS_LIST.stream()
                .map(s -> s.toLowerCase(Locale.ROOT))
                .skip(1);
    }

    @Test
    void testIsZero() {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.fromRoman(""));
    }

    // c3b1 ... c3b4000
    @ParameterizedTest
    @MethodSource
    void testAllValues(String input) {
        assertEquals(ROMAN_NUMERALS_LIST.indexOf(input),
                RomanConverter1.fromRoman(input));
    }

    private static Stream<String> testAllValues() {
        return ROMAN_NUMERALS_LIST.stream()
                .skip(1);
    }

    // c4b1, c4b2
    @Test
    void testInvalidInputTrue() {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.fromRoman("f"));
    }

    @Test
    void testInvalidInputFalse() {
        assertEquals(3, RomanConverter1.fromRoman("III"));
    }

    // c5b1, c5b2
    @Test
    void testSubtractiveNotationTrue() {
        assertEquals(4, RomanConverter1.fromRoman("IV"));
    }

    @Test
    void testSubtractiveNotationFalse() {
        assertEquals(4, RomanConverter1.fromRoman("IIII"));
    }
}

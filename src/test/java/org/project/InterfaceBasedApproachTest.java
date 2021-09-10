package org.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InterfaceBasedApproachTest {
    /* String */

    // c1b1
    @Test
    void testNullString() {
        assertThrows(NullPointerException.class,
                () -> RomanConverter1.fromRoman(null));
    }

    // c1b2
    @Test
    void testNonNullString() {
        assertEquals(1, RomanConverter1.fromRoman("I"));
    }

    // c2b1, c2b2, c2b3
    @Test
    void testLengthZero() {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.fromRoman(""));
    }

    @Test
    void testLengthOne() {
        assertEquals(1, RomanConverter1.fromRoman("I"));
    }

    @Test
    void testLengthGreaterThanOne() {
        assertEquals(3, RomanConverter1.fromRoman("III"));
    }

    /* Integer */

    // c1b1, c1b2, c1b3
    @Test
    void testRelationOfValueToZeroLessThan() {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.toRoman(-1));
    }

    @Test
    void testRelationOfValueToZeroGreaterThan() {
        assertEquals("I", RomanConverter1.toRoman(1));
    }

    @Test
    void testRelationOfValueToZeroIsZero() {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.toRoman(0));
    }

    // c2b1, c2b2
    @ParameterizedTest
    @ValueSource(ints = {Integer.MAX_VALUE, Integer.MAX_VALUE -1})
    void testMaxInt(int input) {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.toRoman(input));
    }

    // c3b1, c3b2
    @ParameterizedTest
    @ValueSource(ints = {Integer.MIN_VALUE, Integer.MIN_VALUE + 1})
    void testMinInt(int input) {
        assertThrows(NoSuchElementException.class,
                () -> RomanConverter1.toRoman(input));
    }

    // c4b1, c4b2
    @Test
    void testInputNullInteger() {
        assertThrows(NullPointerException.class,
                () -> RomanConverter1.toRoman(null));    }

    @Test
    void testInputNonNullInteger() {
        assertEquals("I", RomanConverter1.toRoman(1));
    }
}

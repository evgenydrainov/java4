package com.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenderTest {

    @Test
    void parsesMale() {
        assertEquals(Gender.MALE, Gender.fromString("Male"));
    }

    @Test
    void parsesFemale() {
        assertEquals(Gender.FEMALE, Gender.fromString("Female"));
    }

    @Test
    void parseIsCaseInsensitive() {
        assertEquals(Gender.MALE, Gender.fromString("MALE"));
        assertEquals(Gender.FEMALE, Gender.fromString("female"));
    }

    @Test
    void trimsWhitespace() {
        assertEquals(Gender.MALE, Gender.fromString("  male  "));
    }

    @Test
    void throwsOnUnknownValue() {
        assertThrows(IllegalArgumentException.class, () -> Gender.fromString("Other"));
    }

    @Test
    void throwsOnNull() {
        assertThrows(IllegalArgumentException.class, () -> Gender.fromString(null));
    }
}

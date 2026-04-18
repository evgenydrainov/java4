package com.student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DivisionRegistryTest {

    @Test
    void createsNewDivisionForNewName() {
        DivisionRegistry registry = new DivisionRegistry();
        Division d = registry.getOrCreate("A");
        assertEquals("A", d.getName());
        assertTrue(d.getId() > 0);
    }

    @Test
    void returnsSameInstanceForSameName() {
        DivisionRegistry registry = new DivisionRegistry();
        Division first = registry.getOrCreate("A");
        Division second = registry.getOrCreate("A");
        assertSame(first, second);
    }

    @Test
    void differentNamesGetDifferentIds() {
        DivisionRegistry registry = new DivisionRegistry();
        Division a = registry.getOrCreate("A");
        Division b = registry.getOrCreate("B");
        assertNotEquals(a.getId(), b.getId());
    }

    @Test
    void sizeReflectsUniqueNames() {
        DivisionRegistry registry = new DivisionRegistry();
        registry.getOrCreate("A");
        registry.getOrCreate("B");
        registry.getOrCreate("A");
        assertEquals(2, registry.size());
    }
}

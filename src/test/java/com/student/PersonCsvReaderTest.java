package com.student;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PersonCsvReaderTest {

    @Test
    void readsAllRowsFromFile() throws Exception {
        DivisionRegistry registry = new DivisionRegistry();
        List<Person> people = new PersonCsvReader(registry).read("foreign_names.csv");
        assertFalse(people.isEmpty());
    }

    @Test
    void parsesFirstPersonCorrectly() throws Exception {
        DivisionRegistry registry = new DivisionRegistry();
        List<Person> people = new PersonCsvReader(registry).read("foreign_names.csv");
        Person first = people.get(0);
        assertEquals(28281, first.getId());
        assertEquals("Aahan", first.getName());
        assertEquals(Gender.MALE, first.getGender());
        assertEquals(LocalDate.of(1970, 5, 15), first.getBirthDate());
        assertEquals(4800L, first.getSalary());
        assertEquals("I", first.getDivision().getName());
    }

    @Test
    void deduplicatesDivisionsAcrossPeople() throws Exception {
        DivisionRegistry registry = new DivisionRegistry();
        List<Person> people = new PersonCsvReader(registry).read("foreign_names.csv");
        long uniqueDivisions = people.stream().map(Person::getDivision).distinct().count();
        assertEquals(registry.size(), uniqueDivisions);
    }
}

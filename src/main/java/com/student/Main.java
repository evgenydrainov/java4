package com.student;

import java.util.List;

public class Main {

    private static final String CSV_RESOURCE = "foreign_names.csv";

    public static void main(String[] args) throws Exception {
        DivisionRegistry registry = new DivisionRegistry();
        List<Person> people = new PersonCsvReader(registry).read(CSV_RESOURCE);

        people.forEach(System.out::println);

        System.out.println();
        System.out.println("Divisions:");
        registry.all().forEach(System.out::println);

        System.out.println();
        System.out.println("Total people: " + people.size());
        System.out.println("Total divisions: " + registry.size());
    }
}

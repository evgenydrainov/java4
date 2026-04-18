package com.student;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DivisionRegistry registry = new DivisionRegistry();
        List<Person> people = new PersonCsvReader(registry).read("foreign_names.csv");

        people.forEach(System.out::println);
        System.out.println("Total people: " + people.size());
        System.out.println("Total divisions: " + registry.size());
    }
}

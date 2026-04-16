package com.student;

import java.time.LocalDate;

public class Person {

    private final int id;
    private final String name;
    private final Gender gender;
    private final Division division;
    private final long salary;
    private final LocalDate birthDate;

    public Person(int id, String name, Gender gender, Division division, long salary, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Division getDivision() {
        return division;
    }

    public long getSalary() {
        return salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Person{id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", division=" + division.getName() +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                '}';
    }
}

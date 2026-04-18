package com.student;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final char SEPARATOR = ';';
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) throws Exception {
        String resourcePath = "foreign_names.csv";
        List<Person> people = new ArrayList<>();
        DivisionRegistry registry = new DivisionRegistry();

        InputStream in = Main.class.getClassLoader().getResourceAsStream(resourcePath);
        if (in == null) {
            throw new FileNotFoundException(resourcePath);
        }
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in, StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder().withSeparator(SEPARATOR).build())
                .withSkipLines(1)
                .build()) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                int id = Integer.parseInt(row[0].trim());
                String name = row[1].trim();
                Gender gender = Gender.fromString(row[2]);
                LocalDate birthDate = LocalDate.parse(row[3].trim(), DATE_FORMAT);
                Division division = registry.getOrCreate(row[4].trim());
                long salary = Long.parseLong(row[5].trim());
                people.add(new Person(id, name, gender, division, salary, birthDate));
            }
        }

        people.forEach(System.out::println);
        System.out.println("Total people: " + people.size());
        System.out.println("Total divisions: " + registry.size());
    }
}

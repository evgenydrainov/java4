package com.student;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonCsvReader {

    private static final char SEPARATOR = ';';
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final DivisionRegistry registry;

    public PersonCsvReader(DivisionRegistry registry) {
        this.registry = registry;
    }

    public List<Person> read(String resourcePath) throws IOException, CsvValidationException {
        List<Person> people = new ArrayList<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (in == null) {
            throw new FileNotFoundException(resourcePath);
        }
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(in, StandardCharsets.UTF_8))
                .withCSVParser(new CSVParserBuilder().withSeparator(SEPARATOR).build())
                .withSkipLines(1)
                .build()) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                people.add(parseRow(row));
            }
        }
        return people;
    }

    private Person parseRow(String[] row) {
        int id = Integer.parseInt(row[0].trim());
        String name = row[1].trim();
        Gender gender = Gender.fromString(row[2]);
        LocalDate birthDate = LocalDate.parse(row[3].trim(), DATE_FORMAT);
        Division division = registry.getOrCreate(row[4].trim());
        long salary = Long.parseLong(row[5].trim());
        return new Person(id, name, gender, division, salary, birthDate);
    }
}

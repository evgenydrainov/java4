package com.student;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Main {

    private static final char SEPARATOR = ';';

    public static void main(String[] args) throws Exception {
        String resourcePath = "foreign_names.csv";
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
                System.out.println(Arrays.toString(row));
            }
        }
    }
}

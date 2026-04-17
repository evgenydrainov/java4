package com.student;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Gender value is null");
        }
        switch (value.trim().toLowerCase()) {
            case "male":
                return MALE;
            case "female":
                return FEMALE;
            default:
                throw new IllegalArgumentException("Unknown gender: " + value);
        }
    }
}

package com.jedrzejewski.genderdetector;

public enum Gender {
    FEMALE("FEMALE"),
    MALE("MALE"),
    INCONCLUSIVE("INCONCLUSIVE");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

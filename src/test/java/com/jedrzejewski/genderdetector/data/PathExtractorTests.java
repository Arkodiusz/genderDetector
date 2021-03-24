package com.jedrzejewski.genderdetector.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PathExtractorTests {

    @Test
    void shouldExtractPathToFileWithFemaleTokens() {

        //Given
        PathExtractor pathExtractor = new PathExtractor();

        //When
        String path = pathExtractor.getPathTo("female.txt");

        //Then
        assertNotEquals("", path);
    }

    @Test
    void shouldExtractPathToFileWithMaleTokens() {

        //Given
        PathExtractor pathExtractor = new PathExtractor();

        //When
        String path = pathExtractor.getPathTo("male.txt");

        //Then
        assertNotEquals("", path);
    }
}

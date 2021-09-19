package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jedrzejewski.genderdetector.data.PathExtractor.FEMALE_TOKENS;
import static com.jedrzejewski.genderdetector.data.PathExtractor.MALE_TOKENS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PathExtractorTests {

    @Autowired
    PathExtractor pathExtractor;

    @Test
    void shouldExtractPathToFileWithFemaleTokens() {

        //Given
        String path = "";
        boolean exception = false;

        //When
        try {
            path = pathExtractor.getPathTo(FEMALE_TOKENS);
        } catch (PathExtractorException e) {
            e.printStackTrace();
            exception = true;
        }

        //Then
        assertNotEquals("", path);
        assertFalse(exception);
    }

    @Test
    void shouldExtractPathToFileWithMaleTokens() {

        //Given
        String path = "";
        boolean exception = false;

        //When
        try {
            path = pathExtractor.getPathTo(MALE_TOKENS);
        } catch (PathExtractorException e) {
            e.printStackTrace();
            exception = true;
        }

        //Then
        assertNotEquals("", path);
        assertFalse(exception);
    }
}

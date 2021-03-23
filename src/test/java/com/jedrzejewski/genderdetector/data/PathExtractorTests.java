package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PathExtractorTests {

    @Test
    public void shouldExtractPathToFileWithFemaleTokens() {

        //Given
        PathExtractor pathExtractor = new PathExtractor();
        String path = "";
        boolean exception = false;

        //When
        try {
            path = pathExtractor.getPathTo("female.txt");
        } catch (PathExtractorException e) {
            e.printStackTrace();
            exception = true;
        }

        //Then
        assertNotEquals("", path);
        assertFalse(exception);
    }

    @Test
    public void shouldExtractPathToFileWithMaleTokens() {

        //Given
        PathExtractor pathExtractor = new PathExtractor();
        String path = "";
        boolean exception = false;

        //When
        try {
            path = pathExtractor.getPathTo("male.txt");
        } catch (PathExtractorException e) {
            e.printStackTrace();
            exception = true;
        }

        //Then
        assertNotEquals("", path);
        assertFalse(exception);
    }

    @Test
    public void shouldThrowPathExtractorException() {

        //Given
        PathExtractor pathExtractor = new PathExtractor();
        String fileName = "notExistingFile";

        //When
        Exception exception = assertThrows(PathExtractorException.class, () -> pathExtractor.getPathTo(fileName));

        String expectedMessage = "File \"" + fileName + "\" not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

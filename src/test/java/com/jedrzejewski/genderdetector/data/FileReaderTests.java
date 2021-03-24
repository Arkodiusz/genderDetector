package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class FileReaderTests {

    @Test
    void shouldOpenAndCloseInputStreamOnFileWithFemaleTokensWithoutExceptions() {

        //Given
        boolean exception = false;
        String path = new PathExtractor().getPathTo("female.txt");
        FileReader fileReader = new FileReader() {};

        //When
        try {
            fileReader.setup(path);
            fileReader.close();
        } catch (FileReaderException e) {
            exception = true;
        }

        //Then
        assertFalse(exception);
    }

    @Test
    void shouldOpenAndCloseInputStreamOnFileWithMaleTokensWithoutExceptions() {

        //Given
        boolean exception = false;
        String path = new PathExtractor().getPathTo("male.txt");
        FileReader fileReader = new FileReader() {};

        //When
        try {
            fileReader.setup(path);
            fileReader.close();
        } catch (FileReaderException e) {
            exception = true;
        }

        //Then
        assertFalse(exception);
    }

    @Test
    void shouldThrowFileReaderExceptionDuringOpeningStream() {

        //Given
        String path = "wrong path";
        FileReader fileReader = new FileReader() {};

        //When
        Exception exception = assertThrows(FileReaderException.class, () -> fileReader.setup(path));

        String expectedMessage = "Failed to open input stream on file at " + path;
        String actualMessage = exception.getMessage();

        //Then
        assertTrue(actualMessage.contains(expectedMessage));


    }
}
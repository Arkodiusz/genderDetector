package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FileStreamerForRetrievingTokenListTests {

    @Autowired
    FileStreamerForRetrievingTokenList fileStreamer;

    @Test
    void shouldThrowExceptionWhenGivenWrongFileName() {

        //Given
        String fileName = "wrongFileName";

        //When
        Exception exception = assertThrows(FileStreamerException.class, () -> fileStreamer.streamFile(fileName));

        String expectedMessage = FileStreamerException.ERR_NOT_FOUND;
        String actualMessage = exception.getMessage();

        //Then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FileStreamerForRetrievingTokenListTests {

    @Test
    void shouldThrowExceptionWhenGivenWrongFileName() {

        //Given
        String fileName = "wrongFileName";
        FileStreamerForRetrievingTokenList fileStreamer = new FileStreamerForRetrievingTokenList();

        //When
        Exception exception = assertThrows(FileStreamerException.class, () -> fileStreamer.streamFile(fileName));

        String expectedMessage = "File streamer could not find file \"" + fileName + ".txt\"";
        String actualMessage = exception.getMessage();

        //Then
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

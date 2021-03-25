package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileReaderForRetrievingTokenListTests {

    @Test
    void shouldReturnListOfFemaleTokens() throws FileReaderException, PathExtractorException {

        //Given
        FileReaderForRetrievingTokenList fileReader = new FileReaderForRetrievingTokenList();

        //When
        List<String> tokenList = fileReader.readFile(new PathExtractor().getPathTo("female.txt"));

        String expectedFirstToken = "Ada";
        String expectedLastToken = "Zyta";

        String actualFirstToken = tokenList.get(0);
        String actualLastToken = tokenList.get(tokenList.size()-1);

        //Then
        assertEquals(expectedFirstToken, actualFirstToken);
        assertEquals(expectedLastToken, actualLastToken);
    }

    @Test
    void shouldReturnListOfMaleTokens() throws FileReaderException, PathExtractorException {

        //Given
        FileReaderForRetrievingTokenList fileReader = new FileReaderForRetrievingTokenList();

        //When
        List<String> tokenList = fileReader.readFile(new PathExtractor().getPathTo("male.txt"));

        String expectedFirstToken = "Aaron";
        String expectedLastToken = "Zygmunt";

        String actualFirstToken = tokenList.get(0);
        String actualLastToken = tokenList.get(tokenList.size()-1);

        //Then
        assertEquals(expectedFirstToken, actualFirstToken);
        assertEquals(expectedLastToken, actualLastToken);
    }

}

package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileReaderForComparingAllTokensTests {

    @Test
    void shouldCountOneOccurrenceInFemaleTokensWhenGivenSingleFemaleToken() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        String[] providedTokens = {"Maria"};

        //When
        int femaleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToMale);

        //Then
        assertEquals(1, femaleOccurrenceCounter);
        assertEquals(0, maleOccurrenceCounter);
    }

    @Test
    void shouldCountOneOccurrenceInMaleTokensWhenGivenSingleMaleToken() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        String[] providedTokens = {"Jan"};

        //When
        int femaleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToMale);

        //Then
        assertEquals(0, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }

    @Test
    void shouldCountTwoFemaleTokensAndOneMaleInGivenName() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        String[] providedTokens = {"Anna", "Zbigniew", "Gertruda"};

        //When
        int femaleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToMale);

        //Then
        assertEquals(2, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }

    @Test
    void shouldCountOneFemaleTokensAndOneMaleInGivenName() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        String[] providedTokens = {"Jan", "Maria", "Rokita"};

        //When
        int femaleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToMale);

        //Then
        assertEquals(1, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }

    @Test
    void shouldCountZeroFemaleTokensAndZeroMaleTokensWhenOnlyUnknownTokensInGivenName() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        String[] providedTokens = {"Janek", "Tomek", "Olka"};

        //When
        int femaleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToMale);

        //Then
        assertEquals(0, femaleOccurrenceCounter);
        assertEquals(0, maleOccurrenceCounter);
    }

    @Test
    void shouldIgnoreUpperAndLowerCase() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        String[] providedTokens = {"JAN", "maRiA", "Rokita"};

        //When
        int femaleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compareAllTokens(providedTokens, pathToMale);

        //Then
        assertEquals(1, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }
}
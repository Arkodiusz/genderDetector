package com.jedrzejewski.genderdetector.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.jedrzejewski.genderdetector.data.PathExtractor.FEMALE_TOKENS;
import static com.jedrzejewski.genderdetector.data.PathExtractor.MALE_TOKENS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FileReaderForComparingTokensTests {

    @Autowired
    PathExtractor pathExtractor;

    @Autowired
    FileReaderForComparingTokens fileReader;

    @Test
    void shouldCountOneOccurrenceInFemaleTokensWhenGivenSingleFemaleToken() {

        //Given
        String pathToMale = pathExtractor.getPathTo(MALE_TOKENS);
        String pathToFemale = pathExtractor.getPathTo(FEMALE_TOKENS);

        String[] providedTokens = {"Maria"};

        //When
        int femaleOccurrenceCounter = fileReader.compare(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compare(providedTokens, pathToMale);

        //Then
        assertEquals(1, femaleOccurrenceCounter);
        assertEquals(0, maleOccurrenceCounter);
    }

    @Test
    void shouldCountOneOccurrenceInMaleTokensWhenGivenSingleMaleToken() {

        //Given
        String pathToMale = pathExtractor.getPathTo(MALE_TOKENS);
        String pathToFemale = pathExtractor.getPathTo(FEMALE_TOKENS);

        String[] providedTokens = {"Jan"};

        //When
        int femaleOccurrenceCounter = fileReader.compare(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compare(providedTokens, pathToMale);

        //Then
        assertEquals(0, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }

    @Test
    void shouldCountTwoFemaleTokensAndOneMaleInGivenName() {

        //Given
        String pathToMale = pathExtractor.getPathTo(MALE_TOKENS);
        String pathToFemale = pathExtractor.getPathTo(FEMALE_TOKENS);

        String[] providedTokens = {"Anna", "Zbigniew", "Gertruda"};

        //When
        int femaleOccurrenceCounter = fileReader.compare(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compare(providedTokens, pathToMale);

        //Then
        assertEquals(2, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }

    @Test
    void shouldCountOneFemaleTokensAndOneMaleInGivenName() {

        //Given
        String pathToMale = pathExtractor.getPathTo(MALE_TOKENS);
        String pathToFemale = pathExtractor.getPathTo(FEMALE_TOKENS);

        String[] providedTokens = {"Jan", "Maria", "Rokita"};

        //When
        int femaleOccurrenceCounter = fileReader.compare(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compare(providedTokens, pathToMale);

        //Then
        assertEquals(1, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }

    @Test
    void shouldCountZeroFemaleTokensAndZeroMaleTokensWhenOnlyUnknownTokensInGivenName() {

        //Given
        String pathToMale = pathExtractor.getPathTo(MALE_TOKENS);
        String pathToFemale = pathExtractor.getPathTo(FEMALE_TOKENS);

        String[] providedTokens = {"Janek", "Tomek", "Olka"};

        //When
        int femaleOccurrenceCounter = fileReader.compare(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compare(providedTokens, pathToMale);

        //Then
        assertEquals(0, femaleOccurrenceCounter);
        assertEquals(0, maleOccurrenceCounter);
    }

    @Test
    void shouldIgnoreUpperAndLowerCase() {

        //Given
        String pathToMale = pathExtractor.getPathTo(MALE_TOKENS);
        String pathToFemale = pathExtractor.getPathTo(FEMALE_TOKENS);

        String[] providedTokens = {"JAN", "maRiA", "Rokita"};

        //When
        int femaleOccurrenceCounter = fileReader.compare(providedTokens, pathToFemale);
        int maleOccurrenceCounter = fileReader.compare(providedTokens, pathToMale);

        //Then
        assertEquals(1, femaleOccurrenceCounter);
        assertEquals(1, maleOccurrenceCounter);
    }
}
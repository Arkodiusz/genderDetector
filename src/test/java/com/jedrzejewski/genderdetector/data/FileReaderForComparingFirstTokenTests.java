package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FileReaderForComparingFirstTokenTests {

    @Test
    void shouldDetectMaleByComparingOnlyFirstTokenOfGivenName() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingFirstToken fileReader = new FileReaderForComparingFirstToken();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        //When
        boolean male1 = fileReader.compareOnlyFirstToken("Jan", pathToMale);
        boolean male2 = fileReader.compareOnlyFirstToken("Aaron", pathToMale);
        boolean male3 = fileReader.compareOnlyFirstToken("Zygmunt", pathToMale);
        boolean female = fileReader.compareOnlyFirstToken("Jan", pathToFemale);

        //Then
        assertAll(
                () -> assertTrue(male1),
                () -> assertTrue(male2),
                () -> assertTrue(male3),
                () -> assertFalse(female)
        );
    }

    @Test
    void shouldDetectFemaleByComparingOnlyFirstTokenOfGivenName() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingFirstToken fileReader = new FileReaderForComparingFirstToken();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        //When
        boolean female1 = fileReader.compareOnlyFirstToken("Anna", pathToFemale);
        boolean female2 = fileReader.compareOnlyFirstToken("Ada", pathToFemale);
        boolean female3 = fileReader.compareOnlyFirstToken("Zyta", pathToFemale);
        boolean male = fileReader.compareOnlyFirstToken("Anna", pathToMale);

        //Then
        assertAll(
                () -> assertTrue(female1),
                () -> assertTrue(female2),
                () -> assertTrue(female3),
                () -> assertFalse(male)
        );
    }

    @Test
    void shouldRejectUnknownTokens() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingFirstToken fileReader = new FileReaderForComparingFirstToken();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        //When
        boolean female1 = fileReader.compareOnlyFirstToken("Annaa", pathToFemale);
        boolean female2 = fileReader.compareOnlyFirstToken("Ann", pathToFemale);
        boolean female3 = fileReader.compareOnlyFirstToken("Zyt", pathToFemale);
        boolean female4 = fileReader.compareOnlyFirstToken("Zytaa", pathToFemale);

        boolean male1 = fileReader.compareOnlyFirstToken("Jann", pathToMale);
        boolean male2 = fileReader.compareOnlyFirstToken("Ja", pathToMale);
        boolean male3 = fileReader.compareOnlyFirstToken("Aaro", pathToMale);
        boolean male4 = fileReader.compareOnlyFirstToken("Yzgmunt", pathToMale);

        //Then
        assertAll(
                () -> assertFalse(female1),
                () -> assertFalse(female2),
                () -> assertFalse(female3),
                () -> assertFalse(female4),
                () -> assertFalse(male1),
                () -> assertFalse(male2),
                () -> assertFalse(male3),
                () -> assertFalse(male4)
        );
    }

    @Test
    void shouldIgnoreUpperAndLowerCase() throws PathExtractorException, FileReaderException {

        //Given
        FileReaderForComparingFirstToken fileReader = new FileReaderForComparingFirstToken();
        String pathToMale = new PathExtractor().getPathTo("male.txt");
        String pathToFemale = new PathExtractor().getPathTo("female.txt");

        //When
        boolean female1 = fileReader.compareOnlyFirstToken("AnnA", pathToFemale);
        boolean female2 = fileReader.compareOnlyFirstToken("anna", pathToFemale);
        boolean male1 = fileReader.compareOnlyFirstToken("JAN", pathToMale);
        boolean male2 = fileReader.compareOnlyFirstToken("jAn", pathToMale);

        //Then
        assertAll(
                () -> assertTrue(female1),
                () -> assertTrue(female2),
                () -> assertTrue(male1),
                () -> assertTrue(male2)
        );
    }
}

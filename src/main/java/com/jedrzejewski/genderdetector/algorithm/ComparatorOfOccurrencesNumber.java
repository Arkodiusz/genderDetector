package com.jedrzejewski.genderdetector.algorithm;

import com.jedrzejewski.genderdetector.Gender;
import com.jedrzejewski.genderdetector.data.FileReaderForCountingOccurrences;
import com.jedrzejewski.genderdetector.data.PathExtractor;
import org.springframework.stereotype.Component;

import static com.jedrzejewski.genderdetector.Gender.*;
import static com.jedrzejewski.genderdetector.data.PathExtractor.FEMALE_TOKENS;
import static com.jedrzejewski.genderdetector.data.PathExtractor.MALE_TOKENS;

@Component
public final class ComparatorOfOccurrencesNumber {

    final PathExtractor pathExtractor;
    final FileReaderForCountingOccurrences fileReader;

    public ComparatorOfOccurrencesNumber(PathExtractor pathExtractor, FileReaderForCountingOccurrences fileReader) {
        this.pathExtractor = pathExtractor;
        this.fileReader = fileReader;
    }

    public Gender testFirstToken(String name) {
        return countOccurrencesInTokensLists(retrieveFirstToken(name));
    }

    public Gender testAllTokens(String name) {
        return countOccurrencesInTokensLists(retrieveAllTokens(name));
    }

    private Gender countOccurrencesInTokensLists(String[] providedTokens) {
        String femaleTokens = pathExtractor.getPathTo(FEMALE_TOKENS);
        String maleTokens = pathExtractor.getPathTo(MALE_TOKENS);

        int countOfFemaleTokensInName = fileReader.countOccurrence(providedTokens, femaleTokens);
        int countOfMaleTokens = fileReader.countOccurrence(providedTokens, maleTokens);

        if (countOfFemaleTokensInName > countOfMaleTokens) {
            return FEMALE;
        } else if (countOfFemaleTokensInName < countOfMaleTokens) {
            return MALE;
        } else {
            return INCONCLUSIVE;
        }
    }

    private String[] retrieveFirstToken(String name) {
        String[] tokensInName = name.split(" ");
        return new String[]{tokensInName[0]};
    }

    private String[] retrieveAllTokens(String name) {
        return name.split(" ");
    }
}

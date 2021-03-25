package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.data.FileReaderForComparingTokens;
import com.jedrzejewski.genderdetector.data.PathExtractor;
import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.data.FileReaderForRetrievingTokenList;
import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderDetectorService {

    public String detectGender(String name, String variant) throws FileReaderException, WrongParameterException, PathExtractorException {
        if (variant.equals("1")) {
            return countOccurrencesInTokensLists(retrieveFirstToken(name));
        } else if (variant.equals("2")) {
            return countOccurrencesInTokensLists(retrieveAllTokens(name));
        }
        throw new WrongParameterException("Provided variant is out of bounds. Available options 1 or 2.");
    }

    public List<String> showTokens(String gender) throws FileReaderException, PathExtractorException {
        List<String> tokenList = new ArrayList<>();
        FileReaderForRetrievingTokenList fileReader = new FileReaderForRetrievingTokenList();

        if (gender.equals("female")) {
            tokenList.addAll(fileReader.readFile(getPathToFemaleTokens()));
        } else {
            tokenList.addAll(fileReader.readFile(getPathToMaleTokens()));
        }
        return tokenList;
    }

    private String countOccurrencesInTokensLists(String[] providedTokens) throws FileReaderException, PathExtractorException {
        FileReaderForComparingTokens fileReader = new FileReaderForComparingTokens();
        int countOfFemaleTokensInName = fileReader.compare(providedTokens, getPathToFemaleTokens());
        int countOfMaleTokens = fileReader.compare(providedTokens, getPathToMaleTokens());

        if (countOfFemaleTokensInName > countOfMaleTokens) return "FEMALE";
        else if (countOfFemaleTokensInName < countOfMaleTokens) return "MALE";
        else return "INCONCLUSIVE";
    }

    private String[] retrieveFirstToken(String name) {
        String[] tokensInName = name.split(" ");
        return new String[]{tokensInName[0]};
    }

    private String[] retrieveAllTokens(String name) {
        return name.split(" ");
    }

    private String getPathToFemaleTokens() throws PathExtractorException {
        PathExtractor pathExtractor = new PathExtractor();
        return pathExtractor.getPathTo("female.txt");
    }

    private String getPathToMaleTokens() throws PathExtractorException {
        PathExtractor pathExtractor = new PathExtractor();
        return pathExtractor.getPathTo("male.txt");
    }
}

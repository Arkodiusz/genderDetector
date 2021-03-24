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

    private String pathToMaleTokens;
    private String pathToFemaleTokens;

    public GenderDetectorService() throws PathExtractorException {
        PathExtractor pathExtractor = new PathExtractor();
        this.pathToFemaleTokens = pathExtractor.getPathTo("female.txt");
        this.pathToMaleTokens = pathExtractor.getPathTo("male.txt");
    }

    public String detectGender(String name, int variant) throws FileReaderException, WrongParameterException {
        if (variant == 1) {
            return detectGenderByComparingCountOfOccurrencesInTokensList(retrieveFirstToken(name));
        } else if (variant == 2) {
            return detectGenderByComparingCountOfOccurrencesInTokensList(retrieveAllTokens(name));
        }
        throw new WrongParameterException("Provided variant is out of bounds");
    }

    public List<String> showTokens(String gender) throws FileReaderException {
        List<String> tokenList = new ArrayList<>();
        FileReaderForRetrievingTokenList fileReader = new FileReaderForRetrievingTokenList();

        if (gender.equals("female")) {
            tokenList.addAll(fileReader.readFile(pathToFemaleTokens));
        } else {
            tokenList.addAll(fileReader.readFile(pathToMaleTokens));
        }
        return tokenList;
    }

    private String detectGenderByComparingCountOfOccurrencesInTokensList(String[] providedTokens) throws FileReaderException {
        FileReaderForComparingTokens fileReader = new FileReaderForComparingTokens();
        int countOfFemaleTokensInName = fileReader.compare(providedTokens, pathToFemaleTokens);
        int countOfMaleTokens = fileReader.compare(providedTokens, pathToMaleTokens);

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
}

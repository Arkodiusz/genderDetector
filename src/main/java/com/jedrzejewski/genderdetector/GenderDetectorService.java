package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.data.FileReaderForComparingAllTokens;
import com.jedrzejewski.genderdetector.data.FileReaderForComparingFirstToken;
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
            return detectGenderByComparingOnlyFirstToken(name);
        } else if (variant == 2) {
            return detectGenderByComparingAllTokens(name);
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

    private String detectGenderByComparingOnlyFirstToken(String name) throws FileReaderException {
        FileReaderForComparingFirstToken fileReader = new FileReaderForComparingFirstToken();
        String providedToken = retrieveFirstToken(name);

        if (fileReader.compareOnlyFirstToken(providedToken, pathToFemaleTokens)) return "FEMALE";
        else if (fileReader.compareOnlyFirstToken(providedToken, pathToMaleTokens)) return "MALE";
        else return "INCONCLUSIVE";
    }

    private String detectGenderByComparingAllTokens(String name) throws FileReaderException {
        FileReaderForComparingAllTokens fileReader = new FileReaderForComparingAllTokens();
        String[] providedTokens = splitNameToTokens(name);
        int countOfFemaleTokensInName = fileReader.compareAllTokens(providedTokens, pathToFemaleTokens);
        int countOfMaleTokens = fileReader.compareAllTokens(providedTokens, pathToMaleTokens);

        if (countOfFemaleTokensInName > countOfMaleTokens) return "FEMALE";
        else if (countOfFemaleTokensInName < countOfMaleTokens) return "MALE";
        else return "INCONCLUSIVE";
    }

    private String retrieveFirstToken(String name) {
        String[] tokensInName = name.split(" ");
        return tokensInName[0];
    }

    private String[] splitNameToTokens(String name) {
        return name.split(" ");
    }
}

package com.jedrzejewski.genderdetector;

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
        if (fileReader.compareOnlyFirstToken(name, pathToFemaleTokens)) return "FEMALE";
        else if (fileReader.compareOnlyFirstToken(name, pathToMaleTokens)) return "MALE";
        else return "INCONCLUSIVE";
    }

    private String detectGenderByComparingAllTokens(String name) {
        return "INCONCLUSIVE";
    }
}

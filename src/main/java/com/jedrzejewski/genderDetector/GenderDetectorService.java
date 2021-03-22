package com.jedrzejewski.genderDetector;

import com.jedrzejewski.genderDetector.data.FileReaderForComparingTokens;
import com.jedrzejewski.genderDetector.exceptions.FileReaderException;
import com.jedrzejewski.genderDetector.data.FileReaderForRetrievingTokenList;
import com.jedrzejewski.genderDetector.exceptions.WrongParameterException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderDetectorService {

    private String pathToMaleTokens;
    private String pathToFemaleTokens;

    public GenderDetectorService() throws FileReaderException {
        this.pathToFemaleTokens = getPathTo("female.txt");
        this.pathToMaleTokens = getPathTo("male.txt");
    }

    public String detectGender(String name, int variant) throws FileReaderException, WrongParameterException {
        FileReaderForComparingTokens fileReader = new FileReaderForComparingTokens();

        switch(variant) {
            case 1:
                if (fileReader.compareOnlyFirstToken(name, pathToFemaleTokens)) return "FEMALE";
                else if (fileReader.compareOnlyFirstToken(name, pathToMaleTokens)) return "MALE";
                else return "INCONCLUSIVE";

            default:
                throw new WrongParameterException("Provided variant is out of bounds");
        }
    }

    public List<String> showTokens() throws FileReaderException {
        List<String> tokenList = new ArrayList<>();
        FileReaderForRetrievingTokenList fileReader = new FileReaderForRetrievingTokenList();
        tokenList.addAll(fileReader.readFile(pathToFemaleTokens));
        tokenList.addAll(fileReader.readFile(pathToMaleTokens));
        return tokenList;
    }

    private String getPathTo(String fileName) throws FileReaderException {
        ClassLoader classLoader = getClass().getClassLoader();
        String path;
        try {
            path = classLoader.getResource(fileName).getPath();
        } catch (NullPointerException e) {
            throw new FileReaderException("File \"" + fileName + "\" not found");
        }
        return path;
    }
}

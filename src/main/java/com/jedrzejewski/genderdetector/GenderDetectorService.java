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
}

package com.jedrzejewski.genderDetector;

import com.jedrzejewski.genderDetector.exceptions.FileReaderException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderDetectorService {

    public String detectGender() {

        return "INCONCLUSIVE";
    }

    public List<String> showTokens() throws FileReaderException {

        List<String> tokenList = new ArrayList<>();
        FileReader fileReader = new FileReader();
        tokenList.addAll(fileReader.readFile(getPathTo("male.txt")));
        tokenList.addAll(fileReader.readFile(getPathTo("female.txt")));
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

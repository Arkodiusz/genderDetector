package com.jedrzejewski.genderDetector.data;

import com.jedrzejewski.genderDetector.exceptions.FileReaderException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderForRetrievingTokenList extends FileReader {

    public List<String> readFile(String path) throws FileReaderException {
        List<String> tokenList = new ArrayList<>();
        setup(path);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                tokenList.add(line);
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            throw new FileReaderException("Failed to read data from file at " + path);
        } finally {
            close();
        }
        return tokenList;
    }
}

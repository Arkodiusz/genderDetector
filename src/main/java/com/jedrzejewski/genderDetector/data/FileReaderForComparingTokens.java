package com.jedrzejewski.genderDetector.data;

import com.jedrzejewski.genderDetector.exceptions.FileReaderException;

import java.io.IOException;

public class FileReaderForComparingTokens extends FileReader{

    public boolean compareOnlyFirstToken(String name, String path) throws FileReaderException {
        boolean tokenFound = false;
        setup(path);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.toLowerCase().equals(retrieveFirstToken(name).toLowerCase())) {
                    tokenFound = line.toLowerCase().equals(retrieveFirstToken(name).toLowerCase());
                    break;
                }
            }
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            throw new FileReaderException("Failed to read data from file at " + path);
        } finally {
            close();
        }
        return tokenFound;
    }

    private String retrieveFirstToken(String name) {
        String[] tokensInName = name.split(" ");
        return tokensInName[0];
    }

}

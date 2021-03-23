package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;

import java.io.IOException;

public class FileReaderForComparingFirstToken extends FileReader{

    public boolean compareOnlyFirstToken(String providedToken, String path) throws FileReaderException {
        boolean tokenFound = false;
        setup(path);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                tokenFound = line.equalsIgnoreCase(providedToken);
                if (tokenFound) break;
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
}
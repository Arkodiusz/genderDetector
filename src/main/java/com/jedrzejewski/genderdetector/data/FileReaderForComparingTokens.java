package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;

import java.io.IOException;

public class FileReaderForComparingTokens extends FileReader {

    public int compare(String[] providedTokens, String path) throws FileReaderException {
        int occurrenceCounter = 0;
        setup(path);
        try {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                for (String providedToken : providedTokens) {
                    if (line.equalsIgnoreCase(providedToken)) {
                        occurrenceCounter++;
                        break;
                    }
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
        return occurrenceCounter;
    }
}
package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public final class FileReaderForCountingOccurrences {

    public int countOccurrence(final String[] providedTokens, final String path) {

        try (FileInputStream inputStream = new FileInputStream(path)) {
            int occurrenceCounter = 0;

            try (Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                while (sc.hasNextLine()) {
                    boolean containsName = tokensContainsName(providedTokens, sc.nextLine());
                    occurrenceCounter += containsName ? 1 : 0;
                }
            }
            return occurrenceCounter;

        } catch (IOException e) {
            throw new FileReaderException(FileReaderException.ERR_FILE_SCANNING);
        }
    }

    private boolean tokensContainsName(String[] name, String line) {

        for (String providedToken : name) {
            if (line.equalsIgnoreCase(providedToken)) {
                return true;
            }
        }
        return false;
    }
}
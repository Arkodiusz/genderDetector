package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Component
public final class FileReaderForComparingTokens {

    public int compare(final String[] providedTokens, final String path) {

        try (FileInputStream inputStream = new FileInputStream(path)) {
            int occurrenceCounter = 0;

            try (Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    for (String providedToken : providedTokens) {
                        if (line.equalsIgnoreCase(providedToken)) {
                            occurrenceCounter++;
                            break;
                        }
                    }
                }
            }
            return occurrenceCounter;

        } catch (IOException e) {
            throw new FileReaderException(FileReaderException.ERR_FILE_SCANNING);
        }
    }
}
package com.jedrzejewski.genderDetector;

import com.jedrzejewski.genderDetector.exceptions.FileReaderException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class FileReader {

    private FileInputStream inputStream = null;
    private Scanner sc = null;

    public List<String> readFile(String path) throws FileReaderException {
        List<String> tokenList = new ArrayList<>();
        if (setup(path)) {
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
        }
        return tokenList;
    }

    private boolean setup(String path) throws FileReaderException {
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "windows-1250");
        } catch (IOException e) {
            throw new FileReaderException("Failed to open input stream on file at " + path);
        }
        return true;
    }

    private void close() throws FileReaderException {

        if (inputStream != null) {
            try {
                System.out.println(inputStream);
                inputStream.close();
                System.out.println(inputStream);
            } catch (IOException e) {
                throw new FileReaderException("Error during closing file");
            }
        }
        if (sc != null) {
            sc.close();
        }
    }
}

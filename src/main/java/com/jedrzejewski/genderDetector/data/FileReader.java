package com.jedrzejewski.genderDetector.data;

import com.jedrzejewski.genderDetector.exceptions.FileReaderException;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

@Component
public abstract class FileReader {

    private FileInputStream inputStream = null;
    protected Scanner sc = null;

    protected boolean setup(String path) throws FileReaderException {
        try {
            FileInputStream inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, "windows-1250");
        } catch (IOException e) {
            throw new FileReaderException("Failed to open input stream on file at " + path);
        }
        return true;
    }

    protected void close() throws FileReaderException {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new FileReaderException("Error during closing file");
            }
        }
        if (sc != null) {
            sc.close();
        }
    }
}

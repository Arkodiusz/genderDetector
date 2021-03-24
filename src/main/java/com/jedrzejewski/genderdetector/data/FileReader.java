package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public abstract class FileReader {

    private FileInputStream inputStream = null;
    protected Scanner sc = null;

    protected void setup(String path) throws FileReaderException {
        try {
            inputStream = new FileInputStream(path);
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileReaderException("Failed to open input stream on file at " + path);
        }
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

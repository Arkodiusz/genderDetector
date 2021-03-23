package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;

public class PathExtractor {

    public String getPathTo(String fileName) throws FileReaderException {
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

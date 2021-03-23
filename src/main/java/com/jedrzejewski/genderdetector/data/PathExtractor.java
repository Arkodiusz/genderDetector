package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;

public class PathExtractor {

    public String getPathTo(String fileName) throws PathExtractorException {
        ClassLoader classLoader = getClass().getClassLoader();
        String path;
        try {
            path = classLoader.getResource(fileName).getPath();
        } catch (NullPointerException e) {
            throw new PathExtractorException("File \"" + fileName + "\" not found");
        }
        return path;
    }
}

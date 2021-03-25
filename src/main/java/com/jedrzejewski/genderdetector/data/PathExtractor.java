package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;

import java.net.URL;

public final class PathExtractor {

    public String getPathTo(String fileName) throws PathExtractorException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) return resource.getPath();
        throw new PathExtractorException("File \"" + fileName + "\" not found");
    }
}

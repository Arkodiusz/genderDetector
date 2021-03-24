package com.jedrzejewski.genderdetector.data;

import java.net.URL;

public class PathExtractor {

    public String getPathTo(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) return resource.getPath();
        return "";
    }
}

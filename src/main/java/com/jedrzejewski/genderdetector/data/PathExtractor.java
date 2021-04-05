package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public final class PathExtractor {

    public static final String FEMALE_TOKENS = "female.txt";
    public static final String MALE_TOKENS = "male.txt";

    public String getPathTo(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource != null) {
            return resource.getPath();
        }
        throw new PathExtractorException(PathExtractorException.ERR_NOT_FOUND);
    }


}

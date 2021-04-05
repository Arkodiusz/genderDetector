package com.jedrzejewski.genderdetector.exceptions;

public class PathExtractorException extends RuntimeException {

    public static final String ERR_NOT_FOUND = "File not found.";

    public PathExtractorException(String message) {
        super(message);
    }
}

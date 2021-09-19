package com.jedrzejewski.genderdetector.exceptions;

public class FileStreamerException extends RuntimeException {

    public static final String ERR_NOT_FOUND = "File streamer could not find file.";
    public static final String ERR_STREAMING = "File streamer could not stream data from file.";

    public FileStreamerException(String message) {
        super(message);
    }
}

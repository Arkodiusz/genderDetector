package com.jedrzejewski.genderdetector.exceptions;

public class FileReaderException extends RuntimeException {

    public static final String ERR_FILE_SCANNING = "Error encountered while trying to compare tokens.";

    public FileReaderException(String message) {
        super(message);
    }
}

package com.jedrzejewski.genderdetector.exceptions;

public class WrongParameterException extends RuntimeException {

    public static final String ERR_OUT_OF_BOUNDS = "Provided variant is out of bounds. Available options 1 or 2.";

    public WrongParameterException(String message) {
        super(message);
    }
}

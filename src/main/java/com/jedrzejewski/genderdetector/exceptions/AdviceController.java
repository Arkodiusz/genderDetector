package com.jedrzejewski.genderdetector.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler({WrongParameterException.class})
    public ResponseEntity<String> handleWrongParameterException(WrongParameterException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({FileReaderException.class})
    public ResponseEntity<String> handleFileReaderException(FileReaderException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({PathExtractorException.class})
    public ResponseEntity<String> handlePathExtractorException(PathExtractorException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }

    @ExceptionHandler({FileStreamerException.class})
    public ResponseEntity<byte[]> handleFileStreamerException(FileStreamerException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage().getBytes());
    }
}

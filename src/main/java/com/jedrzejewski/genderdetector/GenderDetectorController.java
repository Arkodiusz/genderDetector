package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class GenderDetectorController {

    final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping(value = "/gender/{name}")
    public ResponseEntity<String> detectGender(
            @PathVariable String name,
            @RequestParam(defaultValue = "1") String variant)
            throws FileReaderException, WrongParameterException, PathExtractorException {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.detectGender(name, variant));
    }
}

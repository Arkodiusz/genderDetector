package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.exceptions.PathExtractorException;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gender")
public class GenderDetectorController {

    final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping(value = "{name}")
    public ResponseEntity<String> detectGender(@PathVariable String name, @RequestParam(defaultValue = "1") String variant)
            throws FileReaderException, WrongParameterException, PathExtractorException {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.detectGender(name, variant));
    }

    @GetMapping(value = "tokens/{gender}")
    public List<String> showTokens(@PathVariable String gender) {
        List<String> tokenList = new ArrayList<>();
        try {
            tokenList = service.showTokens(gender);
        } catch (FileReaderException | PathExtractorException e) {
            e.printStackTrace();
        }
        return tokenList;
    }
}

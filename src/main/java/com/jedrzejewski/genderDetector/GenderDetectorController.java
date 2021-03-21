package com.jedrzejewski.genderDetector;

import com.jedrzejewski.genderDetector.exceptions.FileReaderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gender")
public class GenderDetectorController {

    @Autowired
    GenderDetectorService service;

    @GetMapping(value = "detect")
    public String detectGender(@RequestParam String name, @RequestParam int variant) {

        return service.detectGender();
    }

    @GetMapping(value = "tokens")
    public List<String> showTokens() {
        List<String> tokenList = new ArrayList<>();
        try {
            tokenList = service.showTokens();
        } catch (FileReaderException e) {
            e.printStackTrace();
        }
        return tokenList;
    }
}

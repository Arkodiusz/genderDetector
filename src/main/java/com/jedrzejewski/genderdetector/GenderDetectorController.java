package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.exceptions.FileReaderException;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/gender")
public class GenderDetectorController {

    final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping(value = "detect")
    public String detectGender(@RequestParam String name, @RequestParam int variant) {

        try {
            return service.detectGender(name, variant);
        } catch (FileReaderException | WrongParameterException e) {
            e.printStackTrace();
            return "ERROR";
        }
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

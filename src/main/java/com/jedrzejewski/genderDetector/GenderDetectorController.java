package com.jedrzejewski.genderDetector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/gender")
public class GenderDetectorController {

    @Autowired
    GenderDetectorService service;

    @GetMapping(value = "detectGender")
    public String detectGender(@RequestParam String name, @RequestParam int variant) {

        return service.detectGender();
    }

    @GetMapping(value = "showTokens")
    public List<String> showTokens() {

        return service.showTokens();
    }
}

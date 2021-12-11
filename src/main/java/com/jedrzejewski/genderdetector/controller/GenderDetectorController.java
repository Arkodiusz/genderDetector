package com.jedrzejewski.genderdetector.controller;

import com.jedrzejewski.genderdetector.data.Response;
import com.jedrzejewski.genderdetector.service.GenderDetectorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class GenderDetectorController {

    final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping(value = "/gender/{name}")
    public Response detectGender(
            @PathVariable String name,
            @RequestParam(defaultValue = "1") String variant) {

        return service.detectGender(name, variant);
    }
}

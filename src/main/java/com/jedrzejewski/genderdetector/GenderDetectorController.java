package com.jedrzejewski.genderdetector;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class GenderDetectorController {

    final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping(value = "/gender/{name}")
    public ResponseDTO detectGender(
            @PathVariable String name,
            @RequestParam(defaultValue = "1") String variant) {

        return service.detectGender(name, variant);
    }
}

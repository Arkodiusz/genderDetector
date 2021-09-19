package com.jedrzejewski.genderdetector;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/gender")
public class GenderDetectorController {

    final GenderDetectorService service;

    public GenderDetectorController(GenderDetectorService service) {
        this.service = service;
    }

    @GetMapping(value = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Gender detectGender(
            @PathVariable String name,
            @RequestParam(defaultValue = "1") String variant) {

        return service.detectGender(name, variant);
    }

    @GetMapping(value = "tokens/{gender}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> showTokens(
            @PathVariable String gender) {

        return ResponseEntity.
                status(HttpStatus.OK)
                .body(service.showTokens(gender));
    }
}

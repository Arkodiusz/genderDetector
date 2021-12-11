package com.jedrzejewski.genderdetector.controller;

import com.jedrzejewski.genderdetector.data.Response;
import com.jedrzejewski.genderdetector.data.TokenMapper;
import com.jedrzejewski.genderdetector.service.GenderDetectorService;
import com.jedrzejewski.genderdetector.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class StaticWebPageController {
    private static final Logger log = LoggerFactory.getLogger(StaticWebPageController.class);

    final TokenMapper tokenMapper;
    final TokenService tokenService;
    final GenderDetectorService genderDetectorService;
    private Response response;

    public StaticWebPageController(TokenMapper tokenMapper, TokenService tokenService, GenderDetectorService genderDetectorService) {
        this.tokenMapper = tokenMapper;
        this.tokenService = tokenService;
        this.genderDetectorService = genderDetectorService;
        this.response = new Response();
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("maleTokens", tokenMapper.mapToTokenDtoList(tokenService.getMaleTokens()));
        model.put("femaleTokens", tokenMapper.mapToTokenDtoList(tokenService.getFemaleTokens()));
        model.put("response", response.getDesignation());
        return "index";
    }

    @PostMapping("/predict")
    public String predict(
            @RequestParam(name="name", required = false) String name,
            @RequestParam(name="variant", defaultValue = "1") String variant) {
        log.info("GUI: requested predict for \"{}\" with variant {}", name, variant);
        response = genderDetectorService.detectGender(name, variant);
        return "redirect:/";
    }


}
package com.jedrzejewski.genderdetector.controller;

import com.jedrzejewski.genderdetector.data.Response;
import com.jedrzejewski.genderdetector.data.TokenDto;
import com.jedrzejewski.genderdetector.data.TokenMapper;
import com.jedrzejewski.genderdetector.service.GenderDetectorService;
import com.jedrzejewski.genderdetector.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class StaticWebPageController {
    private static final Logger log = LoggerFactory.getLogger(StaticWebPageController.class);
    private static final String HOME = "redirect:/";
    private static final int TOKENS_PAGINATION_LIMIT = 15;
    private int tokensPaginationMaleOffset = 0;
    private int tokensPaginationFemaleOffset = 0;
    List<TokenDto> maleTokens = new ArrayList<>();
    List<TokenDto> femaleTokens = new ArrayList<>();
    final TokenMapper tokenMapper;
    final TokenService tokenService;
    final GenderDetectorService genderDetectorService;
    private Response response;

    public StaticWebPageController(TokenMapper tokenMapper, TokenService tokenService, GenderDetectorService genderDetectorService) {
        this.tokenMapper = tokenMapper;
        this.tokenService = tokenService;
        this.genderDetectorService = genderDetectorService;
        this.response = new Response();
        setMaleTokens(0);
        setFemaleTokens(0);
    }

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("maleTokens", maleTokens);
        model.put("femaleTokens", femaleTokens);
        model.put("response", response.getDesignation());
        model.put("tokensPaginationMaleOffset", tokensPaginationMaleOffset);
        model.put("tokensPaginationFemaleOffset", tokensPaginationFemaleOffset);
        return "index";
    }

    @GetMapping("/predict")
    public String predict(
            @RequestParam(name="name", required = false) String name,
            @RequestParam(name="variant", defaultValue = "1") String variant) {
        log.info("GUI: requested predict for \"{}\" with variant {}", name, variant);
        response = genderDetectorService.detectGender(name, variant);
        return HOME;
    }

    @GetMapping("/malePreviousPage")
    public String setMalePreviousPage() {
        if (tokensPaginationMaleOffset<=0) {
            tokensPaginationMaleOffset = 0;
            return HOME;
        }
        tokensPaginationMaleOffset -= TOKENS_PAGINATION_LIMIT;
        setMaleTokens(tokensPaginationMaleOffset);
        return HOME;
    }

    @GetMapping("/maleNextPage")
    public String setMaleNextPage() {
        if (maleTokens.size()< TOKENS_PAGINATION_LIMIT) {
            return HOME;
        }
        tokensPaginationMaleOffset += TOKENS_PAGINATION_LIMIT;
        setMaleTokens(tokensPaginationMaleOffset);
        return HOME;
    }

    @GetMapping("/femalePreviousPage")
    public String setFemalePreviousPage() {
        if (tokensPaginationFemaleOffset<=0) {
            tokensPaginationFemaleOffset = 0;
            return HOME;
        }
        tokensPaginationFemaleOffset -= TOKENS_PAGINATION_LIMIT;
        setFemaleTokens(tokensPaginationFemaleOffset);
        return HOME;
    }

    @GetMapping("/femaleNextPage")
    public String setFemaleNextPage() {
        if (femaleTokens.size()< TOKENS_PAGINATION_LIMIT) {
            return HOME;
        }
        tokensPaginationFemaleOffset += TOKENS_PAGINATION_LIMIT;
        setFemaleTokens(tokensPaginationFemaleOffset);
        return HOME;
    }

    private void setMaleTokens(int offset) {
        maleTokens = tokenMapper.mapToTokenDtoList(tokenService.getTokensWithPagination('m', TOKENS_PAGINATION_LIMIT, offset));
    }

    private void setFemaleTokens(int offset) {
        femaleTokens = tokenMapper.mapToTokenDtoList(tokenService.getTokensWithPagination('f', TOKENS_PAGINATION_LIMIT, offset));
    }

}
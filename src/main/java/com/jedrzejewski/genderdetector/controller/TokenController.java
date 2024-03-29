package com.jedrzejewski.genderdetector.controller;

import com.jedrzejewski.genderdetector.data.TokenMapper;
import com.jedrzejewski.genderdetector.data.Token;
import com.jedrzejewski.genderdetector.data.TokenDto;
import com.jedrzejewski.genderdetector.service.TokenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api")
public class TokenController {

    final TokenService service;
    final TokenMapper mapper;

    public TokenController(TokenService service, TokenMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/tokens")
    public List<TokenDto> getAllTokens() {
        return mapper.mapToTokenDtoList(service.getAllTokens());
    }

    @GetMapping(value = "/tokens/{id}")
    public TokenDto getToken(@PathVariable Long id) {
        return mapper.mapToTokenDto(service.getToken(id));
    }

    @GetMapping(value = "/tokens/male")
    public List<TokenDto> getMaleTokens() {
        return mapper.mapToTokenDtoList(service.getTokens('m'));
    }

    @GetMapping(value = "/tokens/female")
    public List<TokenDto> getFemaleTokens() {
        return mapper.mapToTokenDtoList(service.getTokens('f'));
    }

    @PostMapping(value = "/tokens", consumes = APPLICATION_JSON_VALUE)
    public void createToken(@RequestBody TokenDto tokenDto) {
        service.createToken(mapper.mapToToken(tokenDto));
    }

    @PostMapping(value = "/tokens/fill", consumes = APPLICATION_JSON_VALUE)
    public void createTokens(@RequestBody List<TokenDto> tokenDtoList) {
        service.createTokens(mapper.mapToTokenList(tokenDtoList));
    }

    @PutMapping(value = "/tokens", consumes = APPLICATION_JSON_VALUE)
    public Token updateRoute(@RequestBody TokenDto tokenDto) {
        return service.updateToken(mapper.mapToToken(tokenDto));
    }

    @DeleteMapping(value = "/tokens/{id}")
    public void deleteToken(@PathVariable Long id) {
        service.deleteToken(id);
    }
}


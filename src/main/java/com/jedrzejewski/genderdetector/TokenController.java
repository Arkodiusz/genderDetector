package com.jedrzejewski.genderdetector;

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
        return mapper.mapToTokenDtoList(service.getMaleTokens());
    }

    @GetMapping(value = "/tokens/female")
    public List<TokenDto> getFemaleTokens() {
        return mapper.mapToTokenDtoList(service.getFemaleTokens());
    }

    @PostMapping(value = "/tokens", consumes = APPLICATION_JSON_VALUE)
    public void createRoute(@RequestBody TokenDto tokenDto) {
        service.createToken(mapper.mapToToken(tokenDto));
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


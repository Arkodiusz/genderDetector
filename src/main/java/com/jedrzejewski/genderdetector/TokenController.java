package com.jedrzejewski.genderdetector;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TokenController {

    private final TokenService service;
    private final TokenMapper mapper;

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

    @GetMapping(value = "/tokens/nation/{nation}")
    public List<TokenDto> getTokensByNation(@PathVariable String nation) {
        return mapper.mapToTokenDtoList(service.getTokensByNation(nation));
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


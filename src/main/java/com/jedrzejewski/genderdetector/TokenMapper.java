package com.jedrzejewski.genderdetector;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenMapper {
    public Token mapToToken(final TokenDto tokenDto) {
        return new Token(
                tokenDto.getId(),
                tokenDto.getName(),
                tokenDto.getGender(),
                tokenDto.getNation()
        );
    }

    public TokenDto mapToTokenDto(final Token token) {
        return new TokenDto(
                token.getId(),
                token.getName(),
                token.getGender(),
                token.getNation()
        );
    }

    public List<Token> mapToTokenList(final List<TokenDto> tokenList) {
        return tokenList.stream()
                .map(this::mapToToken)
                .collect(Collectors.toList());
    }

    public List<TokenDto> mapToTokenDtoList(final List<Token> tokenList) {
        return tokenList.stream()
                .map(this::mapToTokenDto)
                .collect(Collectors.toList());
    }
}

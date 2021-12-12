package com.jedrzejewski.genderdetector.service;

import com.jedrzejewski.genderdetector.data.Token;
import com.jedrzejewski.genderdetector.exceptions.TokenNotFoundException;
import com.jedrzejewski.genderdetector.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    public Token getToken(final Long id) {
        return tokenRepository.findById(id).orElseThrow(TokenNotFoundException::new);
    }

    public Token createToken(final Token token) {
        return tokenRepository.save(token);
    }

    public List<Token> createTokens(final List<Token> tokens) {
        return tokenRepository.saveAll(tokens);
    }

    public Token updateToken(final Token newToken) {
        Token oldToken = tokenRepository.findById(newToken.getId()).orElseThrow(TokenNotFoundException::new);

        if(!newToken.getName().isEmpty()) {
            oldToken.setName(newToken.getName());
            oldToken.setGender(newToken.getGender());
        }
        return tokenRepository.save(newToken);
    }

    public void deleteToken(final Long id) {
        tokenRepository.deleteById(id);
    }

    public List<Token> getTokens(char gender) {
        return tokenRepository.retrieveByGender(gender);
    }

    public List<Token> getTokensWithPagination(char gender, int limit, int offset) {
        return tokenRepository.retrieveByGenderWithPagination(gender, limit, offset);
    }
}


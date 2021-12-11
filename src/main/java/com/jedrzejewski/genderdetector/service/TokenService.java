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

    public List<Token> getMaleTokens() {
        return tokenRepository.retrieveByGender('m');
    }

    public List<Token> getFemaleTokens() {
        return tokenRepository.retrieveByGender('f');
    }
}


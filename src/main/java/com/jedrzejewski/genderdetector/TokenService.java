package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.exceptions.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
            oldToken.setNation(newToken.getNation());
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

    public List<Token> getTokensByNation(String nation) {
        return tokenRepository.retrieveByNation(nation);
    }
}


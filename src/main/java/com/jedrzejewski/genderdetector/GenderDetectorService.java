package com.jedrzejewski.genderdetector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class GenderDetectorService {

    private final TokenService tokenService;

    public String detectGender(String name, String variant)  {
        if (variant.equals("1")) {
            return countOccurrencesInTokensLists(retrieveFirstToken(name));
        } else if (variant.equals("2")) {
            return countOccurrencesInTokensLists(retrieveAllTokens(name));
        }
        return "";
    }

    private String countOccurrencesInTokensLists(String[] providedTokens) {

        AtomicInteger maleCount = new AtomicInteger(0);
        AtomicInteger femaleCount = new AtomicInteger(0);

        tokenService.getMaleTokens()
                .forEach(t -> {
                    String name = t.getName();
                    for (String providedToken : providedTokens) {
                        if (providedToken.equalsIgnoreCase(name.toUpperCase())) {
                            maleCount.getAndIncrement();
                        }
                    }
                });

        tokenService.getFemaleTokens()
                .forEach(t -> {
                    String name = t.getName();
                    for (String providedToken : providedTokens) {
                        if (providedToken.equalsIgnoreCase(name)) {
                            femaleCount.getAndIncrement();
                        }
                    }
                });

        if (femaleCount.get() > maleCount.get()) return "FEMALE m:f" + maleCount.get() + " : " + femaleCount.get();
        else if (femaleCount.get() < maleCount.get()) return "MALE m:f" + maleCount.get() + " : " + femaleCount.get();
        else return "INCONCLUSIVE m:f" + maleCount.get() + " : " + femaleCount.get();
    }

    private String[] retrieveFirstToken(String name) {
        String[] tokensInName = name.toUpperCase().split(" ");
        return new String[]{tokensInName[0]};
    }

    private String[] retrieveAllTokens(String name) {
        return name.split(" ");
    }
}

package com.jedrzejewski.genderdetector.service;

import com.jedrzejewski.genderdetector.data.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@AllArgsConstructor
public class GenderDetectorService {

    private final TokenService tokenService;

    public Response detectGender(String name, String variant)  {
        if (variant.equals("2")) {
            return countOccurrencesInTokensLists(retrieveAllTokens(name));
        } else {
            return countOccurrencesInTokensLists(retrieveFirstToken(name));
        }
    }

    private Response countOccurrencesInTokensLists(String[] providedTokens) {

        AtomicInteger maleCount = new AtomicInteger(0);
        AtomicInteger femaleCount = new AtomicInteger(0);

        tokenService.getMaleTokens()
                .forEach(t -> {
                    String name = t.getName();
                    for (String providedToken : providedTokens) {
                        if (providedToken.equalsIgnoreCase(name.toUpperCase())) {
                            maleCount.getAndIncrement();
                            break;
                        }
                    }
                });

        tokenService.getFemaleTokens()
                .forEach(t -> {
                    String name = t.getName();
                    for (String providedToken : providedTokens) {
                        if (providedToken.equalsIgnoreCase(name)) {
                            femaleCount.getAndIncrement();
                            break;
                        }
                    }
                });

        Set<String> tokenSet = new HashSet<>();
        Collections.addAll(tokenSet, providedTokens);
        int length = tokenSet.size();

        double percentage;
        int inconclusiveCount = length-maleCount.get()-femaleCount.get();

        String designation;

        if (femaleCount.get() > maleCount.get()) {
            designation = "FEMALE";
            percentage = ((double)femaleCount.get()/length)*100;
        } else if (femaleCount.get() < maleCount.get()) {
            designation = "MALE";
            percentage = ((double)maleCount.get()/length)*100;
        } else {
            designation = "INCONCLUSIVE";
            percentage = ((double)inconclusiveCount/length)/100;
        }

        return new Response(
                designation,
                maleCount.get(),
                femaleCount.get(),
                inconclusiveCount,
                percentage
        );
    }

    private String[] retrieveFirstToken(String name) {
        String[] tokensInName = name.toUpperCase().split(" ");
        return new String[]{tokensInName[0]};
    }

    private String[] retrieveAllTokens(String name) {
        return name.split(" ");
    }
}

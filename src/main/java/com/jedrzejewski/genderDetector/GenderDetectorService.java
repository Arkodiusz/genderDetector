package com.jedrzejewski.genderDetector;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenderDetectorService {

    public String detectGender() {

        return "INCONCLUSIVE";
    }

    public List<String> showTokens() {

        return new ArrayList<>();
    }
}

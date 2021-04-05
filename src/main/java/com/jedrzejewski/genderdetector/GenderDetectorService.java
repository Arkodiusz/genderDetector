package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.data.FileReaderForComparingTokens;
import com.jedrzejewski.genderdetector.data.FileStreamerForRetrievingTokenList;
import com.jedrzejewski.genderdetector.data.PathExtractor;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.springframework.stereotype.Service;

import static com.jedrzejewski.genderdetector.data.PathExtractor.FEMALE_TOKENS;
import static com.jedrzejewski.genderdetector.data.PathExtractor.MALE_TOKENS;

@Service
public class GenderDetectorService {

    public static final String MALE = "MALE";
    public static final String FEMALE = "FEMALE";
    public static final String INCONCLUSIVE = "INCONCLUSIVE";

    final PathExtractor pathExtractor;

    public GenderDetectorService(PathExtractor pathExtractor) {
        this.pathExtractor = pathExtractor;
    }

    public String detectGender(String name, String variant) {
        if (variant.equals("1")) {
            return countOccurrencesInTokensLists(retrieveFirstToken(name));
        } else if (variant.equals("2")) {
            return countOccurrencesInTokensLists(retrieveAllTokens(name));
        }
        throw new WrongParameterException(WrongParameterException.ERR_OUT_OF_BOUNDS);
    }

    public byte[] showTokens(String gender) {
        FileStreamerForRetrievingTokenList fileStreamer = new FileStreamerForRetrievingTokenList();
        return fileStreamer.streamFile(gender);
    }

    private String countOccurrencesInTokensLists(String[] providedTokens) {
        String femaleTokens = pathExtractor.getPathTo(FEMALE_TOKENS);
        String maleTokens = pathExtractor.getPathTo(MALE_TOKENS);

        FileReaderForComparingTokens fileReader = new FileReaderForComparingTokens();
        int countOfFemaleTokensInName = fileReader.compare(providedTokens, femaleTokens);
        int countOfMaleTokens = fileReader.compare(providedTokens, maleTokens);

        if (countOfFemaleTokensInName > countOfMaleTokens) return FEMALE;
        else if (countOfFemaleTokensInName < countOfMaleTokens) return MALE;
        else return INCONCLUSIVE;
    }

    private String[] retrieveFirstToken(String name) {
        String[] tokensInName = name.split(" ");
        return new String[]{tokensInName[0]};
    }

    private String[] retrieveAllTokens(String name) {
        return name.split(" ");
    }


}

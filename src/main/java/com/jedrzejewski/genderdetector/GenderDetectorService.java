package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.data.FileStreamerForRetrievingTokenList;
import com.jedrzejewski.genderdetector.data.ComparatorOfOccurrencesNumber;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.springframework.stereotype.Service;

@Service
public class GenderDetectorService {

    final ComparatorOfOccurrencesNumber comparatorOfOccurrencesNumber;
    FileStreamerForRetrievingTokenList fileStreamer;

    public GenderDetectorService(ComparatorOfOccurrencesNumber comparatorOfOccurrencesNumber, FileStreamerForRetrievingTokenList fileStreamer) {
        this.comparatorOfOccurrencesNumber = comparatorOfOccurrencesNumber;
        this.fileStreamer = fileStreamer;
    }

    public Gender detectGender(String name, String variant) {
        if (variant.equals("1")) {
            return comparatorOfOccurrencesNumber.testFirstToken(name);
        } else if (variant.equals("2")) {
            return comparatorOfOccurrencesNumber.testAllTokens(name);
        }
        throw new WrongParameterException(WrongParameterException.ERR_OUT_OF_BOUNDS);
    }

    public byte[] showTokens(String gender) {
        return fileStreamer.streamFile(gender);
    }




}

package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileStreamerForRetrievingTokenList {

    public byte[] streamFile(String gender) throws FileStreamerException {
        String fileName = gender + ".txt";
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input != null) {
            try {
                return IOUtils.toByteArray(input);
            } catch (IOException e) {
                throw new FileStreamerException("File streamer could not stream data from file \"" + fileName + "\"");
            }
        }
        return new byte[0];
    }
}

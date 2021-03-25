package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class FileStreamerForRetrievingTokenList {

    public byte[] streamFile(String gender) throws FileStreamerException {
        String fileName = gender + ".txt";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input != null)
                return IOUtils.toByteArray(input);
            else
                throw new FileStreamerException("File streamer could not find file \"" + fileName + "\"");
        } catch (IOException e) {
            throw new FileStreamerException("File streamer could not stream data from file \"" + fileName + "\"");
        }
    }
}

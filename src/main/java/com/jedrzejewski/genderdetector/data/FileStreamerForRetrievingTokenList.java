package com.jedrzejewski.genderdetector.data;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public final class FileStreamerForRetrievingTokenList {

    public byte[] streamFile(final String gender) {
        String fileName = gender + ".txt";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                return IOUtils.toByteArray(input);
            }
            throw new FileStreamerException(FileStreamerException.ERR_NOT_FOUND);

        } catch (IOException e) {
            throw new FileStreamerException(FileStreamerException.ERR_STREAMING);
        }
    }
}

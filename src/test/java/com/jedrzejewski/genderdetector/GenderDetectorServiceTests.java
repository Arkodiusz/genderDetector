package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenderDetectorServiceTests {

    @Autowired
    GenderDetectorService service;

    @ParameterizedTest
    @CsvSource({
            "Jan Maria Rokita, 1, MALE",
            "Jan Jan Maria Maria, 2, INCONCLUSIVE",
            "Anna Zbigniew Gertruda, 1, FEMALE",
            "Anna Zbigniew Gertruda, 2, FEMALE",
            "Anna Zbigniew Gertruda Jan, 2, INCONCLUSIVE",
            "Ania, 1, INCONCLUSIVE",
            "Ania Basia Tomek, 2, INCONCLUSIVE",
            "Tomek Jan, 2, MALE",
            "Tomek Jan, 1, INCONCLUSIVE",
    })
    void shouldReturnMale_whenUsingVariant1_andFirstTokenIsMale(String name, String variant, String expected) {
        //Given & When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(expected, response.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "Jan Maria Rokita",
            "Tomasz"
    })
    void shouldReturnMale_whenUsingVariant1_andFirstTokenIsMale(String name) {
        //Given & When
        Gender response = service.detectGender(name, "1");

        //Then
        assertEquals(Gender.MALE, response);
    }

    @Test
    void shouldThrowException_whenWrongParameterIsUsed() {
        //Given
        String name = "Jan";
        String variant = "0";
        Exception exception = null;

        //When & Then
        try {
            service.detectGender(name, variant);
        } catch (Exception e) {
            exception = e;
        } finally {
            assertNotNull(exception);
            assertEquals(WrongParameterException.class, exception.getClass());
            assertEquals(WrongParameterException.ERR_OUT_OF_BOUNDS, exception.getMessage());
        }
    }

    @Test
    void shouldReturnStreamOfMaleTokens() {
        //Given & When
        byte[] stream = service.showTokens("male");

        //Then
        assertNotNull(stream);
    }

    @Test
    void shouldReturnStreamOfFemaleTokens() {
        //Given & When
        byte[] stream = service.showTokens("female");

        //Then
        assertNotNull(stream);
    }

    @Test
    void shouldThrowExceptionWhenUnknownTokenListDemanded() {
        //Given
        byte[] stream = null;
        Exception exception = null;

        //When & Then
        try {
            stream = service.showTokens("x");
        } catch (Exception e) {
            exception = e;
        } finally {
            assertNull(stream);
            assertNotNull(exception);
            assertEquals(FileStreamerException.class, exception.getClass());
            assertEquals(FileStreamerException.ERR_NOT_FOUND, exception.getMessage());
        }
    }

}
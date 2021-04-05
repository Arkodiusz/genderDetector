package com.jedrzejewski.genderdetector;

import com.jedrzejewski.genderdetector.exceptions.FileStreamerException;
import com.jedrzejewski.genderdetector.exceptions.WrongParameterException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenderDetectorServiceTests {

    @Autowired
    GenderDetectorService service;

    @Test
    void shouldReturnMale_whenUsingVariant1_andFirstTokenIsMale() {
        //Given
        String name = "Jan Maria Rokita";
        String variant = "1";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.MALE, response);
    }

    @Test
    void shouldReturnInconclusive_whenUsingVariant2_andThereAreOneMaleAndOneFemaleToken() {
        //Given
        String name = "Jan Maria Rokita";
        String variant = "2";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.INCONCLUSIVE, response);
    }

    @Test
    void shouldReturnFemale_whenUsingVariant1_andFirstTokenIsFemale() {
        //Given
        String name = "Anna Zbigniew Gertruda";
        String variant = "1";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.FEMALE, response);
    }

    @Test
    void shouldReturnFemale_whenUsingVariant2_andThereAreOneMaleAndTwoFemaleToken() {
        //Given
        String name = "Anna Zbigniew Gertruda";
        String variant = "2";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.FEMALE, response);
    }

    @Test
    void shouldReturnInconclusive_whenUsingVariant2_andThereAreTwoMaleAndTwoFemaleToken() {
        //Given
        String name = "Anna Zbigniew Gertruda Jan";
        String variant = "2";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.INCONCLUSIVE, response);
    }

    @Test
    void shouldReturnInconclusive_whenUsingVariant1_andTokenIsUnknown() {
        //Given
        String name = "Ania";
        String variant = "1";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.INCONCLUSIVE, response);
    }

    @Test
    void shouldReturnInconclusive_whenUsingVariant2_andTokensAreUnknown() {
        //Given
        String name = "Ania Basia Tomek";
        String variant = "2";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.INCONCLUSIVE, response);
    }

    @Test
    void shouldReturnMale_whenUsingVariant2_andFirstTokenIsUnknownAndSecondMale() {
        //Given
        String name = "Tomek Jan";
        String variant = "2";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.MALE, response);
    }

    @Test
    void shouldReturnInconclusive_whenUsingVariant1_andFirstTokenIsUnknownAndSecondMale() {
        //Given
        String name = "Tomek Jan";
        String variant = "1";

        //When
        Gender response = service.detectGender(name, variant);

        //Then
        assertEquals(Gender.INCONCLUSIVE, response);
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
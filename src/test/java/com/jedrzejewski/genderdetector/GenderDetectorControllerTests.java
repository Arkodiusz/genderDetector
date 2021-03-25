package com.jedrzejewski.genderdetector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest( GenderDetectorController.class)
class GenderDetectorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenderDetectorService service;

    @Test
    void shouldReturnMaleWhenDetectingWithFirstVariant() throws Exception {

        // Given
        when(service.detectGender("Jan Maria Rokita", "1")).thenReturn("MALE");

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/Jan Maria Rokita?variant=1")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.content().string("MALE"));
    }

    @Test
    void shouldReturnFemaleWhenDetectingWithFirstVariant() throws Exception {

        // Given
        when(service.detectGender("Anna Zbigniew Gertruda", "1")).thenReturn("FEMALE");

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/Anna Zbigniew Gertruda?variant=1")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.content().string("FEMALE"));
    }

    @Test
    void shouldReturnMaleWhenDetectingWithSecondVariant() throws Exception {

        // Given
        when(service.detectGender("Jan Maria Rokita", "2")).thenReturn("INCONCLUSIVE");

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/Jan Maria Rokita?variant=2")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("INCONCLUSIVE"));
    }

    @Test
    void shouldReturnFemaleWhenDetectingWithSecondVariant() throws Exception {

        // Given
        when(service.detectGender("Anna Zbigniew Gertruda", "2")).thenReturn("FEMALE");

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/Anna Zbigniew Gertruda?variant=2")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().string("FEMALE"));
    }

    @Test
    void shouldUseFirstVariantWhenParameterNotProvided() throws Exception {

        // Given
        when(service.detectGender("Jan Maria Rokita", "1")).thenReturn("MALE");

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/Jan Maria Rokita")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200)) // or isOk()
                .andExpect(MockMvcResultMatchers.content().string("MALE"));
    }

    @Test
    void shouldPerformMaleTokensListingWithStatus200() throws Exception {

        // Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/tokens/male")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldPerformFemaleTokensListingWithStatus200() throws Exception {

        // Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/gender/tokens/female")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
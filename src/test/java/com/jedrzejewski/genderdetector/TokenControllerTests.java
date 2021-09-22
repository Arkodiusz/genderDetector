package com.jedrzejewski.genderdetector;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class TokenControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldPerformMaleTokensListingWithStatus200() throws Exception {
        // Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/tokens/male")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldPerformFemaleTokensListingWithStatus200() throws Exception {
        // Given
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/tokens/female")
                .contentType(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}
package org.example.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddCalculation() throws Exception {
        mockMvc.perform(post("/api/calculator/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 10, \"b\": 5}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15))
                .andExpect(jsonPath("$.operation").value("add"))
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    public void testDivideCalculation() throws Exception {
        mockMvc.perform(post("/api/calculator/divide")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"a\": 20, \"b\": 4, \"operation\": \"divide\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(5))
                .andExpect(jsonPath("$.operation").value("divide"))
                .andExpect(jsonPath("$.success").value(true));
    }
}
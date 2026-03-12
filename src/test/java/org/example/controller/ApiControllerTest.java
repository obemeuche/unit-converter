package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ConversionRequest;
import org.example.model.UnitCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCategories_returnsAllCategories() throws Exception {
        mockMvc.perform(get("/api/categories"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)))
            .andExpect(jsonPath("$", containsInAnyOrder("LENGTH", "WEIGHT", "TEMPERATURE", "VOLUME")));
    }

    @Test
    void getUnits_length_returnsLengthUnits() throws Exception {
        mockMvc.perform(get("/api/units/length"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasItem("meter")))
            .andExpect(jsonPath("$", hasItem("foot")))
            .andExpect(jsonPath("$", hasItem("mile")));
    }

    @Test
    void getUnits_weight_returnsWeightUnits() throws Exception {
        mockMvc.perform(get("/api/units/weight"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasItem("kilogram")))
            .andExpect(jsonPath("$", hasItem("pound")));
    }

    @Test
    void getUnits_invalidCategory_returnsBadRequest() throws Exception {
        mockMvc.perform(get("/api/units/invalid"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void convert_lengthConversion_returnsResult() throws Exception {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.LENGTH, "meter", "foot", 100.0
        );

        mockMvc.perform(post("/api/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.originalValue").value(100.0))
            .andExpect(jsonPath("$.convertedValue").value(closeTo(328.084, 0.001)))
            .andExpect(jsonPath("$.sourceUnit").value("meter"))
            .andExpect(jsonPath("$.targetUnit").value("foot"))
            .andExpect(jsonPath("$.formula").exists());
    }

    @Test
    void convert_temperatureConversion_returnsResult() throws Exception {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.TEMPERATURE, "celsius", "fahrenheit", 100.0
        );

        mockMvc.perform(post("/api/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.convertedValue").value(212.0));
    }

    @Test
    void convert_weightConversion_returnsResult() throws Exception {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.WEIGHT, "kilogram", "pound", 10.0
        );

        mockMvc.perform(post("/api/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.convertedValue").value(closeTo(22.0462, 0.001)));
    }

    @Test
    void convert_volumeConversion_returnsResult() throws Exception {
        ConversionRequest request = new ConversionRequest(
            UnitCategory.VOLUME, "gallon", "liter", 1.0
        );

        mockMvc.perform(post("/api/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.convertedValue").value(closeTo(3.78541, 0.001)));
    }
}

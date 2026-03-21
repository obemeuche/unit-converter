package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index_returnsIndexPage() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(view().name("index"));
    }

    @Test
    void length_returnsLengthPage() throws Exception {
        mockMvc.perform(get("/length"))
            .andExpect(status().isOk())
            .andExpect(view().name("length"))
            .andExpect(model().attributeExists("units"));
    }

    @Test
    void weight_returnsWeightPage() throws Exception {
        mockMvc.perform(get("/weight"))
            .andExpect(status().isOk())
            .andExpect(view().name("weight"))
            .andExpect(model().attributeExists("units"));
    }

    @Test
    void temperature_returnsTemperaturePage() throws Exception {
        mockMvc.perform(get("/temperature"))
            .andExpect(status().isOk())
            .andExpect(view().name("temperature"))
            .andExpect(model().attributeExists("units"));
    }
}

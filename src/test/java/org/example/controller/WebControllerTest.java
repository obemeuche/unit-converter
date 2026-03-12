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
            .andExpect(view().name("index"))
            .andExpect(model().attributeExists("categories"))
            .andExpect(model().attributeExists("unitsByCategory"));
    }

    @Test
    void index_containsAllCategories() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(content().string(org.hamcrest.Matchers.containsString("LENGTH")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("WEIGHT")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("TEMPERATURE")))
            .andExpect(content().string(org.hamcrest.Matchers.containsString("VOLUME")));
    }
}

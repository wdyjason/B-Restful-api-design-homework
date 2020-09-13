package com.thoughtworks.capability.gtb.restfulapidesign.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GroupApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllGroups()throws Exception {
        mockMvc.perform(get("/groups"))
                .andExpect(jsonPath("$", hasSize(6)))
                .andExpect(status().isOk());
    }
}
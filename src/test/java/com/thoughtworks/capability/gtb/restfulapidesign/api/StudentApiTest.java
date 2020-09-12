package com.thoughtworks.capability.gtb.restfulapidesign.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldCreateStudentSuccess() throws Exception {
        String createString = "{\"id\": \"1\", \"name\": \"test\", \"gender\": \"男\", \"note\": \"张三\"}";
        mockMvc.perform(post("/student").content(createString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

}
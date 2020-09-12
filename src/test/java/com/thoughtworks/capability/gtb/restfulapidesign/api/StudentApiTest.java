package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    public Student genStudent(Integer id, GenderType type) {
        return Student.builder()
                .id(id)
                .gender(type)
                .name("test")
                .note("note")
                .build();

    }

    @BeforeEach
    public void setUp() {
        studentRepository.deleteAll();
        studentRepository.save(genStudent(1, GenderType.MALE));
        studentRepository.save(genStudent(2, GenderType.FEMALE));
    }

    @Test
    public void shouldCreateStudentSuccess() throws Exception {
        String createString = "{\"id\": \"1\", \"name\": \"test\", \"gender\": \"男\", \"note\": \"张三\"}";
        mockMvc.perform(post("/students").content(createString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldGetAllStudents() throws Exception {
        mockMvc.perform(get("/students"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].gender", is("男")))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].gender", is("女")))
                .andExpect(jsonPath("$[1].name", is("test")))
                .andExpect(jsonPath("$[1].note", is("note")))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetMaleStudentsSuccess() throws Exception {
        mockMvc.perform(get("/students?gender=男"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].gender", is("男")))
                .andExpect(jsonPath("$[0].name", is("test")))
                .andExpect(jsonPath("$[0].note", is("note")))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetStudentsByIdSuccessfully() throws Exception {
        mockMvc.perform(get("/students/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.gender", is("男")))
                .andExpect(jsonPath("$.name", is("test")))
                .andExpect(jsonPath("$.note", is("note")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateStudentSuccessfully() throws Exception {
        String updateString = "{\"id\": \"1\", \"name\": \"test_c\", \"gender\": \"女\", \"note\": \"des\"}";
        mockMvc.perform(put("/students/").content(updateString).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Student result= studentRepository.findById(1).get();
        Student expectedStudent = new Student(1, "test_c", GenderType.FEMALE, "des");
        assertEquals(expectedStudent, result);
    }

}
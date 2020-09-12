package com.thoughtworks.capability.gtb.restfulapidesign.api;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.excepiton.StudentNotFoundException;
import com.thoughtworks.capability.gtb.restfulapidesign.servcie.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentApi {

    @Autowired
    private StudentService studentService;

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student newStudent) {
        studentService.createStudent(newStudent);
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(@RequestParam(required = false) String gender) {
        return studentService.findAllStudentsWithGender(gender);
    }

    @GetMapping("/students/{id}")
    public Student getAStudent(@PathVariable Integer id) throws StudentNotFoundException {
        return studentService.getAStudent(id);
    }
}

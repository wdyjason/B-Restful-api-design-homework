package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private final List<Student> studentDataSource = new ArrayList<>();

    public Student save(Student student) {
        this.studentDataSource.add(student);
        return student;
    }
}

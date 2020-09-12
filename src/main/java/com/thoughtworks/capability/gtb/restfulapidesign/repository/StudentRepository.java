package com.thoughtworks.capability.gtb.restfulapidesign.repository;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    private final List<Student> studentDataSource = new ArrayList<>();

    public Student save(Student student) {
        this.studentDataSource.add(student);
        return student;
    }

    public List<Student> findAll() {
        return this.studentDataSource;
    }

    public List<Student> findByGender(GenderType gender) {
        return this.studentDataSource.stream()
                .filter( f -> f.getGender() == gender )
                .collect(Collectors.toList());
    }
}

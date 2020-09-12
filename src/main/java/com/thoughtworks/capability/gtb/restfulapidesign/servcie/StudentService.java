package com.thoughtworks.capability.gtb.restfulapidesign.servcie;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.excepiton.StudentNotFoundException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllStudentsWithGender(String gender) {
        if (gender == null) {
            return studentRepository.findAll();
        }
        return studentRepository.findByGender(GenderType.getGenderType(gender));
    }

    public Student getAStudent(int id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            return student.get();
        }
        throw new StudentNotFoundException("this student id not exist!");
    }
}

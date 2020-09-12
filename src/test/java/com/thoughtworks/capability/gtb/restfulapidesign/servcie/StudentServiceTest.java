package com.thoughtworks.capability.gtb.restfulapidesign.servcie;

import com.thoughtworks.capability.gtb.restfulapidesign.domain.GenderType;
import com.thoughtworks.capability.gtb.restfulapidesign.domain.Student;
import com.thoughtworks.capability.gtb.restfulapidesign.excepiton.StudentNotFoundException;
import com.thoughtworks.capability.gtb.restfulapidesign.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    public Student genStudent() {
        return Student.builder()
                .id(123)
                .gender(GenderType.MALE)
                .name("test")
                .note("note")
                .build();

    }

    @Test
    public void shouldCreateStudentSuccess() {
        Student student = genStudent();
        Student returnStudent = genStudent();

        when(studentRepository.save(student)).thenReturn(returnStudent);
        studentService.createStudent(student);

        verify(studentRepository, times(1)).save(student);

    }

    @Test
    public void shouldGetAllStudentsSuccess() {
        Student returnStudent = genStudent();
        Student expectedStudent = genStudent();

        when(studentRepository.findAll()).thenReturn(Arrays.asList(returnStudent));

        List<Student> result = studentService.findAllStudentsWithGender(null);

        assertEquals(Arrays.asList(expectedStudent), result);
    }

    @Test
    public void shouldGetMaleStudentsSuccess() {
        Student returnStudent = genStudent();
        Student expectedStudent = genStudent();

        when(studentRepository.findByGender(GenderType.MALE)).thenReturn(Arrays.asList(returnStudent));

        List<Student> result = studentService.findAllStudentsWithGender("男");

        assertEquals(Arrays.asList(expectedStudent), result);
    }

    @Test
    public void shouldGetStudentsByIdSuccessfully() throws StudentNotFoundException {
        Student returnStudent = genStudent();
        Student expectedStudent = genStudent();

        when(studentRepository.findById(123)).thenReturn(Optional.of(returnStudent));

        Student result = studentService.getAStudent(123);

        assertEquals(expectedStudent, result);
    }

    @Test
    public void shouldGetStudentsByIdFailure() throws StudentNotFoundException {

        when(studentRepository.findById(123)).thenReturn(Optional.empty());

        assertThrows(StudentNotFoundException.class, () -> studentService.getAStudent(123));
    }

    @Test
    public void shouldUpdateStudentSuccessfully() throws Exception {
        Student receiveStudent = genStudent();

        when(studentRepository.updateStudent(receiveStudent)).thenReturn(true);
        studentService.updateStudent(receiveStudent);

        verify(studentRepository, times(1)).updateStudent(receiveStudent);

    }

    @Test
    public void shouldUpdateStudentFailure() throws Exception {
        Student receiveStudent = genStudent();

        when(studentRepository.updateStudent(receiveStudent)).thenReturn(false);

        assertThrows(StudentNotFoundException.class, () -> studentService.updateStudent(receiveStudent));

    }
}
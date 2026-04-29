package com.example.attendance.service;

import com.example.attendance.entity.Student;
import java.util.List;

public interface StudentService {
    Student createStudent(Student student);
    Student getStudentById(String studentId);
    List<Student> getAllStudents();
    List<Student> getStudentsByClass(String className);
    void deleteStudent(String studentId);
}
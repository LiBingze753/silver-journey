package com.example.attentance.service;

import com.example.attentance.entity.Student;

public interface StudentService {
    String createStudent(Student student);
    Student getStudentById(String studentId);
}

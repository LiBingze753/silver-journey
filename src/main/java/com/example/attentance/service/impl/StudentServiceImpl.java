package com.example.attentance.service.impl;

import com.example.attentance.service.StudentService;
import com.example.attentance.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public String createStudent(Student student) {
        if(student.getName() == null || student.getName().isEmpty()){
            throw new RuntimeException("姓名不能为空");
        }
        return "创建成功";
    }

    @Override
    public Student getStudentById(String id) {
        return null;
    }
}

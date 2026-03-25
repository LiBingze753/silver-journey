package com.example.attentance.controller;

import com.example.attentance.entity.Student;
import com.example.attentance.service.StudentService;
import com.example.attentance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/creat")
    public Result<String> createStudent(@RequestBody Student student) {
        String result = studentService.createStudent(student);
        return Result.success(result);
    }

    @GetMapping("/{id}")
    public Result<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        return Result.success(student);
    }
}

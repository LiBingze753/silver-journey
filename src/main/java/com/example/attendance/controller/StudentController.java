package com.example.attendance.controller;

import com.example.attendance.entity.Student;
import com.example.attendance.service.StudentService;
import com.example.attendance.util.Result;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public Result<Student> create(@RequestBody Student student) {
        Student saved = studentService.createStudent(student);
        return Result.success(saved);
    }

    @GetMapping("/{studentId}")
    public Result<Student> getById(@PathVariable String studentId) {
        Student student = studentService.getStudentById(studentId);
        return Result.success(student);
    }

    @GetMapping("/list")
    public Result<List<Student>> listAll() {
        List<Student> students = studentService.getAllStudents();
        return Result.success(students);
    }

    @GetMapping("/class/{className}")
    public Result<List<Student>> getByClass(@PathVariable String className) {
        List<Student> students = studentService.getStudentsByClass(className);
        return Result.success(students);
    }

    @DeleteMapping("/{studentId}")
    public Result<String> delete(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return Result.success("删除成功");
    }
}
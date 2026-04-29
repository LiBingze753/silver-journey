package com.example.attendance.controller;

import com.example.attendance.entity.Student;
import com.example.attendance.repository.StudentRepository;
import com.example.attendance.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * 简单分页（无筛选条件）
     * GET /student/list?page=0&size=10&sortField=studentId&sortDirection=asc
     */
    @GetMapping("/list")
    public Result<Page<Student>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Student> result = studentRepository.findAll(pageable);
        return Result.success(result);
    }

    /**
     * 多条件 + 分页 + 排序查询
     * GET /student/search?className=软件工程1班&name=张三&gender=男&page=0&size=10&sortField=studentId&sortDirection=asc
     */
    @GetMapping("/search")
    public Result<Page<Student>> search(
            @RequestParam(required = false) String className,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "studentId") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortField);
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Student> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (className != null && !className.isEmpty()) {
                predicates.add(cb.equal(root.get("className"), className));
            }
            if (name != null && !name.isEmpty()) {
                predicates.add(cb.like(root.get("name"), "%" + name + "%"));
            }
            if (gender != null && !gender.isEmpty()) {
                predicates.add(cb.equal(root.get("gender"), gender));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Student> result = studentRepository.findAll(spec, pageable);
        return Result.success(result);
    }
}
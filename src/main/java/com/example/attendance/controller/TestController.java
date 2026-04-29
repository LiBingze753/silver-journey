package com.example.attendance.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "管理员专属内容";
    }

    @GetMapping("/teacher")
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public String teacherOrAdmin() {
        return "教师或管理员可访问";
    }

    @GetMapping("/public")
    public String publicAccess() {
        return "任何人都可以访问（无需登录）";
    }
}
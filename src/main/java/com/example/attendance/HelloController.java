package com.example.attendance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello() {
        return "欢迎来到班级考勤系统！";
    }
    @PostMapping("/submit")
    public String submitData() {
        return "数据已提交！";
    }
}

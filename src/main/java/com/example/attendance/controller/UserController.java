package com.example.attendance.controller;

import com.example.attendance.entity.User;
import com.example.attendance.service.UserService;
import com.example.attendance.util.Result;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 构造器注入（无需 @Autowired）
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public Result<String> create(@RequestBody User user) {
        userService.createUser(user);
        return Result.success("用户创建成功");
    }

    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @GetMapping("/username/{username}")
    public Result<User> getByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return Result.success(user);
    }

    @GetMapping("/teachers")
    public Result<List<User>> listTeachers() {
        List<User> teachers = userService.getAllTeachers();
        return Result.success(teachers);
    }

    @GetMapping("/list")
    public Result<List<User>> listAll() {
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success("用户更新成功");
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success("用户删除成功");
    }
}
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public Result<User> create(@RequestBody User user) {
        User saved = userService.createUser(user);
        return Result.success(saved);
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

    @GetMapping("/list")
    public Result<List<User>> listAll() {
        List<User> users = userService.getAllUsers();
        return Result.success(users);
    }

    @GetMapping("/teachers")
    public Result<List<User>> listTeachers() {
        List<User> teachers = userService.getAllTeachers();
        return Result.success(teachers);
    }

    @PutMapping("/update")
    public Result<User> update(@RequestBody User user) {
        User updated = userService.updateUser(user);
        return Result.success(updated);
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return Result.success("删除成功");
    }
}
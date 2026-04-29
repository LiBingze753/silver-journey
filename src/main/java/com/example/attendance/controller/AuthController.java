package com.example.attendance.controller;

import com.example.attendance.dto.RegisterRequest;
import com.example.attendance.entity.User;
import com.example.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, Model model) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("errorMsg", "两次输入的密码不一致");
            return "register";
        }
        if (userRepository.findByUsername(request.getUsername()) != null) {
            model.addAttribute("errorMsg", "用户名已存在");
            return "register";
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRealName(request.getRealName());
        user.setRole(request.getRole() != null ? request.getRole() : "STUDENT");
        userRepository.save(user);
        return "redirect:/login?registered=true";
    }
}
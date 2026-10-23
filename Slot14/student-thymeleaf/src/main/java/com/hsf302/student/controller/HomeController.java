package com.hsf302.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Chuyển hướng người dùng từ trang chủ sang danh sách sinh viên
        return "redirect:/students";
    }
}

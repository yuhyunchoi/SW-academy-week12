package com.nhnacademy.studentmanage.controller;

import com.nhnacademy.studentmanage.repository.StudentRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.UUID;

@Controller
public class StudentLoginController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentLoginController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/login")
    public String loginForm(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        boolean loggedIn = Arrays.stream(cookies)
                .anyMatch(cookie -> "SESSION".equals(cookie.getName()));

        if (loggedIn) {
            return "redirect:/student/view"; // studentView should show student info
        }
        return "loginForm"; // Returns the login form view
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password, HttpServletResponse response) {
        if (studentRepository.matches(id, password)) {
            Cookie sessionCookie = new Cookie("SESSION", UUID.randomUUID().toString());
            sessionCookie.setPath("/");
            sessionCookie.setHttpOnly(true);
            response.addCookie(sessionCookie);
            return "redirect:/student/view?id=" + id;
        }
        return "redirect:/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie sessionCookie = new Cookie("SESSION", null);
        sessionCookie.setMaxAge(0); // Set cookie expiration to immediately remove it
        sessionCookie.setPath("/");
        response.addCookie(sessionCookie);
        return "redirect:/login";
    }
}

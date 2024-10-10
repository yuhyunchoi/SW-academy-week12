package com.nhnacademy.studentmanage.controller;

import com.nhnacademy.studentmanage.domain.Student;
import com.nhnacademy.studentmanage.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentRegisterController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentRegisterController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerForm", new Student());
        return "studentRegister"; // Returns registration form view
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Student student) {
        studentRepository.register(student);
        return "redirect:/student/view"; // Redirect to the student info view
    }
}

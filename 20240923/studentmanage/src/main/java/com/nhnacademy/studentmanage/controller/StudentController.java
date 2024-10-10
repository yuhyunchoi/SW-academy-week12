package com.nhnacademy.studentmanage.controller;

import com.nhnacademy.studentmanage.domain.Student;
import com.nhnacademy.studentmanage.exception.ResourceNotFoundException;
import com.nhnacademy.studentmanage.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public String viewStudentList(Model model){
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "studentList";
    }

    @GetMapping("/{studentId}")
    public String viewStudent(@PathVariable String studentId, Model model){
        Student student = studentRepository.getStudent(studentId);

        if (student == null) {
            throw new ResourceNotFoundException(studentId);
        }

        model.addAttribute("student", Student.createStudent(student));
        return "studentView";
    }

    @GetMapping("/{studentId}/modify")
    public String studentModifyForm(@PathVariable String studentId, Model model){
        Student student = studentRepository.getStudent(studentId);
        if(student == null){
            throw new ResourceNotFoundException(studentId);
        }
        model.addAttribute("student", student);
        return "studentModify";
    }

    @PostMapping("/{studentId}/modify")
    public String studentModify(@PathVariable String studentId,
                                @Valid @ModelAttribute Student student,
                                BindingResult bindingResult,
                                Model model){

        if(bindingResult.hasErrors()){
            return "studentModify";
        }

        try{
            studentRepository.modify(studentId, student);
            return "redirect:/student/" + studentId;
        }catch(Exception e){
            model.addAttribute("error", e.getMessage());
            return "studentModify";
        }
    }

}
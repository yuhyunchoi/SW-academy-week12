package com.nhnacademy.studentmanage.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.studentmanage.domain.Student;
import com.nhnacademy.studentmanage.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void viewStudentList_shouldReturnStudentListPage() throws Exception {
        given(studentRepository.findAll()).willReturn(Arrays.asList(
                new Student("user1", "password", "Lee", "lee@example.com", 90, "Good")
        ));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentList"))
                .andExpect(model().attributeExists("students"));
    }

    @Test
    void viewStudent_shouldReturnStudentViewPage() throws Exception {
        Student student = new Student("user2", "password", "Na", "na@example.com", 80, "Great");
        given(studentRepository.getStudent("user2")).willReturn(student);

        mockMvc.perform(get("/student/user2"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentView"))
                .andExpect(model().attributeExists("student"))
                .andExpect(MockMvcResultMatchers.model().attribute("student", student));
    }

    @Test
    void studentModifyForm_shouldReturnStudentModifyPage() throws Exception {
        Student student = new Student("user2", "password", "Na", "na@example.com", 80, "Great");
        given(studentRepository.getStudent("user2")).willReturn(student);

        mockMvc.perform(get("/student/user2/modify"))
                .andExpect(status().isOk())
                .andExpect(view().name("studentModify"))
                .andExpect(model().attributeExists("student"));
    }

    @Test
    void studentModify_shouldModifyStudentAndRedirect() throws Exception {
        Student modifiedStudent = new Student("user2", "newpassword", "Na", "new@example.com", 85, "Excellent");
        given(studentRepository.getStudent("user2")).willReturn(modifiedStudent);

        mockMvc.perform(post("/student/user2/modify")
                        .param("id", "user2")
                        .param("password", "newpassword")
                        .param("name", "Na")
                        .param("email", "new@example.com")
                        .param("grade", "85")
                        .param("estimate", "Excellent"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/user2"));
    }
}

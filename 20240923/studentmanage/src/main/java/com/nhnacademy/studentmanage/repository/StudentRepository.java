package com.nhnacademy.studentmanage.repository;

import com.nhnacademy.studentmanage.domain.Student;

import java.util.List;

public interface StudentRepository {
    boolean exists(String id);
    boolean matches(String id, String password);
    Student getStudent(String id);
    Student register(Student student);
    Student modify(String originId, Student student);
    List<Student> findAll();
}

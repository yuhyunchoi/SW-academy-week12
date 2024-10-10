package com.nhnacademy.studentmanage.repository;

import com.nhnacademy.studentmanage.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final Map<String, Student> studentMap = new HashMap<String, Student>();

    public StudentRepositoryImpl() {
        studentMap.put("user1", new Student("user1", "0514", "이채영", "chaeyoung@naver.com", 100,"Perfect"));
        studentMap.put("user2", new Student("user2","0601", "이나경", "nakyeong@gmail.com",90, "great"));
        studentMap.put("user3", new Student("user3", "0417", "백지헌", "juheon@daum.net", 80, "good"));
    }

    @Override
    public boolean exists(String id) {
        return studentMap.containsKey(id);
    }

    @Override
    public boolean matches(String id, String password) {
        if(Objects.isNull(id)){
            throw new NullPointerException("id is null");
        }
        Student student = studentMap.get(id);
        return student.getPassword().equals(password);
    }

    @Override
    public Student getStudent(String id) {
        if(Objects.isNull(id)){
            throw new NullPointerException("id is null");
        }
        return studentMap.get(id);
    }

    @Override
    public Student register(Student student) {
        if(exists(student.getId())){
            throw new IllegalArgumentException("student already exists");
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    @Override
    public Student modify(String originId, Student student) {
        Student newStudent = getStudent(student.getId());
        newStudent.setPassword(student.getPassword());
        newStudent.setName(student.getName());
        newStudent.setEmail(student.getEmail());
        newStudent.setGrade(student.getGrade());
        newStudent.setEstimate(student.getEstimate());
        return getStudent(student.getId());
    }
    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());  // 모든 학생 목록 반환
    }
}

package com.nhnacademy.studentmanage.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class Student {
    @NotBlank(message = "{student.id.notblank}")
    private String id;

    @NotBlank(message = "{student.password.notblank}")
    @Size(min = 6, message = "{student.password.size}")
    private String password;

    @NotBlank(message = "{student.name.notblank}")
    private String name;

    @Email(message = "{student.email.invalid}")
    private String email;

    @Min(value = 0, message = "{student.grade.min}")
    @Max(value = 100, message = "{student.grade.max}")
    private int grade;

    @Size(max = 200, message = "{student.estimate.size}")
    private String estimate;

    public Student() {}

    private static final String MASK = "*****";

    public static Student createStudent(Student student) {
        return new Student(student.getId(),
                MASK,
                student.getName(),
                student.getEmail(),
                student.getGrade(),
                student.getEstimate());
    }

}

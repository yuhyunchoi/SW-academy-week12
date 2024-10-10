package com.nhnacademy.studentmanage.domain;

import lombok.Value;

@Value
public class StudentRegisterRequest {
    String id;
    String password;
    String name;
    String email;
    int grade;
    String estimate;
}

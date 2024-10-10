package com.nhnacademy.nhnmart2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String id;
    private String password;
    private String name;

    private static final String MASK = "*****";





}
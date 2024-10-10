package com.nhnacademy.nhnmart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {

    private String userId;
    private String password;
    private String name;

    private static final String MASK = "******";

    public static User create(String userId, String password){
        return new User(userId, password);
    }

    private User(String userId, String password){
        this.userId = userId;
        this.password = password;
    }


}

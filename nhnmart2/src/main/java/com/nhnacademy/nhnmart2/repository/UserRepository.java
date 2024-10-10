package com.nhnacademy.nhnmart2.repository;

import com.nhnacademy.nhnmart2.domain.User;

public interface UserRepository {
    User findById(String id);
    boolean matches(String id, String password);
}

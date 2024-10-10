package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.User;

public interface UserRepository {
    boolean matches(String id, String password);
    User getUser(String id);
}

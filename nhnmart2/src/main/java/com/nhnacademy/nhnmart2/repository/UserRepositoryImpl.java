package com.nhnacademy.nhnmart2.repository;


import com.nhnacademy.nhnmart2.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryImpl() {
        users.put("user1", new User("user1", "1234", "user1"));
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public boolean matches(String id, String password) {
        User user = findById(id);
        return user != null && user.getPassword().equals(password);
    }
}

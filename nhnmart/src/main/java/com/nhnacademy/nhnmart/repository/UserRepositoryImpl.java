package com.nhnacademy.nhnmart.repository;

import com.nhnacademy.nhnmart.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public UserRepositoryImpl() {
        users.put("user1", User.create("user1", "1234"));
    }

    @Override
    public boolean matches(String userId, String password) {
        return Optional.ofNullable(getUser(userId))
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

    @Override
    public User getUser(String userId){
        return users.get(userId);
    }
}

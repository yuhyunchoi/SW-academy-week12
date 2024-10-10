package com.nhnacademy.studentmanage.config;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            boolean loggedIn = Arrays.stream(cookies)
                    .anyMatch(cookie -> "SESSION".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty());

            if (loggedIn) {
                return true; // Proceed if logged in
            }
        }
        // Redirect to login page if not logged in
        response.sendRedirect("/login");
        return false;
    }
}
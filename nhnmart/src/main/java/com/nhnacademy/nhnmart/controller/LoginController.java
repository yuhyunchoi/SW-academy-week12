package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(@CookieValue(value="SESSION", required = false) String sessionId,
                        HttpServletRequest request,
                        Model model){
        if(StringUtils.hasText(sessionId)){
            HttpSession session = request.getSession(false);
            if(session != null && sessionId.equals(session.getId())) {
                model.addAttribute("id", sessionId);
                return "inquiries";
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("userId") String userId,
                          @RequestParam("password") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap){

        if(userRepository.matches(userId, password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("userId", userId);

            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);

            modelMap.put("id", session.getId());
            return "redirect:/inquiries";
        }else{
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        Cookie cookie = new Cookie("SESSION", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/login";
    }


}

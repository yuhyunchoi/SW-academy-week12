package com.nhnacademy.nhnmart2.controller;

import com.nhnacademy.nhnmart2.repository.UserRepository;
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
    public String login(@CookieValue(value="SESSION", required=false) String session,
                        HttpServletRequest request,
                        Model model) {

        if (session != null) {
            model.addAttribute("id", session);
            return "redirect:/posts";
        }
        return "loginForm";

}

    @PostMapping("/login")
    public String doLogin(@RequestParam("id") String id,
                          @RequestParam("pwd") String password,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          ModelMap modelMap){
        if(userRepository.matches(id,password)){
            HttpSession session = request.getSession(true);
            Cookie cookie = new Cookie("SESSION", session.getId());
            response.addCookie(cookie);
            modelMap.addAttribute("id", session.getId());
            return "redirect:/posts";
        }else{
            modelMap.addAttribute("error", "Invalid credentials");
            return "loginForm";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }

}

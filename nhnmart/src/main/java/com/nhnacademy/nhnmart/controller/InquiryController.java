package com.nhnacademy.nhnmart.controller;


import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.InquiryRegisterRequest;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/inquiries")
@Slf4j
public class InquiryController {
    private final InquiryRepository inquiryRepository;

    public InquiryController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping
    public String listInquiries(HttpServletRequest request, Model model ){
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");

        if(Objects.isNull(userId)){
            return "redirect:/login";
        }
        List<Inquiry> inquiries = inquiryRepository.getInquiriesByUserId(userId);
        model.addAttribute("inquiries", inquiries);
        return "inquiries";

    }

    @GetMapping("/{id}")
    public ModelAndView viewInquiry(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("inquiryView");
        Inquiry inquiry = inquiryRepository.getInquiry(id);

        mav.addObject("inquiry", inquiry);
        mav.addObject("userId", inquiry.getUserId());
        return mav;

    }

    @PostMapping
    public ModelAndView registerPost(@ModelAttribute InquiryRegisterRequest inquiryRegisterRequest,
                                     BindingResult bindingResult,
                                     HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();  // 유효성 검사 실패 시 예외 발생
        }
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("User not logged in");
        }

        Inquiry inquiry = inquiryRepository.register(
                inquiryRegisterRequest.getTitle(),
                inquiryRegisterRequest.getCategory(),
                inquiryRegisterRequest.getContent(),
                userId
        );
        ModelAndView mav = new ModelAndView("inquiryRegister");
        mav.addObject("inquiries", inquiry);
        return mav;
    }

}

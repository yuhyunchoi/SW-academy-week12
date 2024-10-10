package com.nhnacademy.nhnmart.controller;

import com.nhnacademy.nhnmart.domain.Inquiry;
import com.nhnacademy.nhnmart.domain.InquiryRegisterRequest;
import com.nhnacademy.nhnmart.repository.InquiryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/inquiries/register")
@Slf4j
public class PostRegisterController {
    private final InquiryRepository inquiryRepository;

    public PostRegisterController(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }

    @GetMapping
    public String userRegisterForm(Model model) {
        model.addAttribute("categories", List.of("불만 접수", "제안", "환불/교환", "칭찬해요", "기타 문의"));
        log.info("success");
        return "postRegister";
    }

    @PostMapping
    public ModelAndView registerPost(@ModelAttribute InquiryRegisterRequest inquiryRegisterRequest,
                               BindingResult bindingResult,
                               HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("Validation errors occurred");
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
                userId // Pass userId
        );

        ModelAndView mav = new ModelAndView("postRegister");
        mav.addObject("inquiry", inquiry);
        return mav;
    }

//    @InitBinder("postRegisterRequest")
//    protected void initBinder(WebDataBinder binder) {
//        binder.addValidators(validator);  // 요청에 대한 유효성 검사기 등록
//    }
}

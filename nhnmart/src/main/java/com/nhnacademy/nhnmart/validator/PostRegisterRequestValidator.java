//package com.nhnacademy.nhnmart.validator;
//
//import com.nhnacademy.nhnmart.domain.InquiryRegisterRequest;
//import jakarta.validation.Validator;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//
//@Component
//public class PostRegisterRequestValidator implements Validator {
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return InquiryRegisterRequest.class.isAssignableFrom(clazz);
//    }
//
//    @Override
//    public void validate(Object target, Errors errors) {
//        InquiryRegisterRequest postRequest = (InquiryRegisterRequest) target;
//
//        // 제목이 비어 있는지 검사
//        if (postRequest.getTitle() == null || postRequest.getTitle().isEmpty()) {
//            errors.rejectValue("title", "title.empty", "제목은 필수입니다.");
//        }
//
//        // 내용이 비어 있는지 검사
//        if (postRequest.getContent() == null || postRequest.getContent().isEmpty()) {
//            errors.rejectValue("content", "content.empty", "내용은 필수입니다.");
//        }
//
//        // 제목 길이 제한 검사 (예: 1~100자)
//        if (postRequest.getTitle() != null && (postRequest.getTitle().length() < 1 || postRequest.getTitle().length() > 100)) {
//            errors.rejectValue("title", "title.size", "제목은 1자 이상 100자 이하여야 합니다.");
//        }
//
//        // 내용 길이 제한 검사 (예: 1~1000자)
//        if (postRequest.getContent() != null && (postRequest.getContent().length() < 1 || postRequest.getContent().length() > 1000)) {
//            errors.rejectValue("content", "content.size", "내용은 1자 이상 1000자 이하여야 합니다.");
//        }
//    }
//}

package com.nhnacademy.nhnmart.domain;

import lombok.Value;

@Value
public class InquiryRegisterRequest {
    String title;
    String category;
    String content;
    String userId;
}

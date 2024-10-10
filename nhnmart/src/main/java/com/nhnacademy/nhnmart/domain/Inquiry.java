package com.nhnacademy.nhnmart.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Inquiry {
    private Long id;
    private String title;
    private String category;
    private String content;
    private String userId;
    private LocalDateTime createdAt;
    private boolean answered;

    public static Inquiry create(String title, String category, String content, String userId) {
        Inquiry inquiry = new Inquiry(null, title, category, content, userId, LocalDateTime.now(), false);
        return inquiry;
    }
}

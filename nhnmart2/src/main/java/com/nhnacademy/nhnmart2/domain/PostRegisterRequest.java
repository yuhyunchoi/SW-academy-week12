package com.nhnacademy.nhnmart2.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostRegisterRequest {
    private long id;
    private String title;
    private String category;
    private String content;
    private LocalDateTime createdAt;
    private User author;
    private List<String> attachments;
}

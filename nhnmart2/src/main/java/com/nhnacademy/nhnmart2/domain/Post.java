package com.nhnacademy.nhnmart2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private long id;
    private String title;
    private String category;
    private String content;
    private LocalDateTime createdAt;
    private User author;
    private List<String> attachments;

    public static Post create(long id,String title, String category, String content,LocalDateTime createdAt, User author, List<String> attachments) {
        return new Post(id, title, category, content, createdAt, author, attachments);
    }

}

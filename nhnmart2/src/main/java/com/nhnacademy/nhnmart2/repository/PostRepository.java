package com.nhnacademy.nhnmart2.repository;

import com.nhnacademy.nhnmart2.domain.Post;
import com.nhnacademy.nhnmart2.domain.User;

import java.util.List;

public interface PostRepository {
    Post register(String title, String category, String content, User author, List<String> attachments);
    Post getPost(long id);
    List<Post> findAll();
    List<Post> findByCategory(String category);
}

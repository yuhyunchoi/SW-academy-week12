package com.nhnacademy.nhnmart2.repository;

import com.nhnacademy.nhnmart2.domain.Post;
import com.nhnacademy.nhnmart2.domain.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, Post> posts = new HashMap<>();
    private long nextId = 1;

    @Override
    public Post register(String title, String category, String content, User author, List<String> attachments) {
        Post post = Post.create(nextId++,title, category, content, LocalDateTime.now(), author, attachments);
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public Post getPost(long id) {
        return posts.get(id);
    }

    @Override
    public List<Post> findAll() {
        return posts.values().stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())  // 최근 글이 상단에 위치
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByCategory(String category) {
        return posts.values().stream()
                .filter(post -> post.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }


}

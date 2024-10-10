package com.nhnacademy.nhnmart2.controller;

import com.nhnacademy.nhnmart2.domain.Post;
import com.nhnacademy.nhnmart2.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String getPostList(@RequestParam(value = "category", required = false) String category, Model model) {
        List<Post> posts;
        if (category != null && !category.isEmpty()) {
            posts = postRepository.findByCategory(category);  // 카테고리로 검색
        } else {
            posts = postRepository.findAll();  // 전체 목록
        }
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        Post post = postRepository.getPost(postId);
        if(post == null){
            model.addAttribute("error", "Post not found");
            return "error/404";
        }
        model.addAttribute("post", post);
        return "postView";
    }

}

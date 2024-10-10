package com.nhnacademy.nhnmart2.controller;


import com.nhnacademy.nhnmart2.domain.Post;
import com.nhnacademy.nhnmart2.domain.PostRegisterRequest;
import com.nhnacademy.nhnmart2.domain.User;
import com.nhnacademy.nhnmart2.repository.PostRepository;
import com.nhnacademy.nhnmart2.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts/register")
public class PostRegisterController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private static final String UPLOAD_DIR = "C:\\Users\\dbgus\\OneDrive\\바탕 화면\\충남대학교";

    public PostRegisterController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String postForm(Model model){
        model.addAttribute("post", new PostRegisterRequest());
        return "postRegister";
    }

    @PostMapping
    public String registerPost(@Valid @ModelAttribute("post") PostRegisterRequest postRequest,
                               BindingResult bindingResult,
                               @RequestParam("files") List<MultipartFile> files,
                               HttpSession session,
                               Model model) throws IOException {
        if(bindingResult.hasErrors()){
            return "postRegister";
        }

        String userId = (String) session.getAttribute("userId");
        User author = userRepository.findById(userId);

        if(author == null){
            return "redirect:/login";
        }
        List<String> attachmentPaths = new ArrayList<>();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                // 파일 확장자 확인 (이미지 파일만 허용)
                String fileName = file.getOriginalFilename();
                if (fileName != null && fileName.matches("([^\\s]+(\\.(?i)(gif|jpg|jpeg|png))$)")) {
                    String path = UPLOAD_DIR + fileName;
                    file.transferTo(Paths.get(path));  // 파일을 지정된 경로로 저장
                    attachmentPaths.add(fileName);
                }
            }
        }

        Post post = postRepository.register(postRequest.getTitle(), postRequest.getCategory(), postRequest.getContent(), author, attachmentPaths);
        return "redirect:/posts";
    }



}

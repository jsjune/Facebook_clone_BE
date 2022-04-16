package com.best2team.facebook_clone_be.controller;

import com.best2team.facebook_clone_be.dto.MsgResponseDto;
import com.best2team.facebook_clone_be.dto.PostResponseDto;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/api/post")
    public MsgResponseDto writePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("image") MultipartFile multipartFile,  @RequestParam("content") String content) throws IOException {

        return postService.writePost(userDetails, multipartFile, content);
    }

    @GetMapping("/api/post/{pageno}")
    public PostResponseDto showAllPost(@PathVariable("pageno") int pageno, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new PostResponseDto(postService.showAllPost(pageno-1, userDetails));
    }


    @DeleteMapping("/api/post/{postid}")
    public MsgResponseDto deletePost(@PathVariable("postid") Long postid) {
        return postService.deletePost(postid);
    }
}

package com.best2team.facebook_clone_be.controller;

import com.best2team.facebook_clone_be.dto.PostRequestDto;
import com.best2team.facebook_clone_be.model.Post;
import com.best2team.facebook_clone_be.repository.PostRepository;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {


    private final PostRepository postRepository;

    //게시글 저장
    @PostMapping("/api/post")
    public void savePost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

       String content = requestDto.getContent();
       String username = userDetails.getUsername();
       Post post = new Post(content,username);
       postRepository.save(post);
    }
}

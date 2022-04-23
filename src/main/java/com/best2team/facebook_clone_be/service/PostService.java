package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.dto.PostEditResponseDto;
import com.best2team.facebook_clone_be.dto.PostListDto;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PostService {
    PostListDto writePost(UserDetailsImpl userDetails, MultipartFile multipartFile, String content) throws IOException;


    Page<PostListDto> showAllPost(int pageno, UserDetailsImpl userDetails);


    PostEditResponseDto editPost(Long postid, MultipartFile multipartFile, String content) throws IOException;


    void deletePost(Long postid);


    Page<PostListDto> getMyPage(int pageno, String username,UserDetailsImpl userDetails);
}

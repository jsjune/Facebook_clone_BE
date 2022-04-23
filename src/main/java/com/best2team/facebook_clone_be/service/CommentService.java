package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.dto.CommentRequestDto;
import com.best2team.facebook_clone_be.dto.CommentResponseDto;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import org.springframework.data.domain.Page;

public interface CommentService {


    CommentResponseDto createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails);


    void updateComment(long commentid, CommentRequestDto requestDto, UserDetailsImpl userDetails);


    void deleteComment(long commentid, UserDetailsImpl userDetails);


    Page<CommentResponseDto> getCommentList(Long postid, int pageno);

}

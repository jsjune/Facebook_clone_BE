package com.best2team.facebook_clone_be.controller;

import com.best2team.facebook_clone_be.dto.CommentRequestDto;
import com.best2team.facebook_clone_be.dto.dto.CommentListResponserDto;
import com.best2team.facebook_clone_be.dto.dto.CommentResponseDto;
import com.best2team.facebook_clone_be.model.Comment;
import com.best2team.facebook_clone_be.repository.CommentRepository;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController
public class CommentController {


    private final CommentService commentService;

    //댓글 등록
    @PostMapping("/api/comment")
    public String createComment(@RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

       return commentService.createComment(requestDto,userDetails);
    }

    // 댓글 조회 ( 전체)
//    @GetMapping("/api/comment")
//    public List<CommentListResponserDto> getComment(@RequestBody CommentRequestDto requestDto){
//        return commentService.getComment(requestDto);
//    }

    @GetMapping("/api/comment")
    public List<Comment> getCommentList(@RequestBody CommentRequestDto requestDto){
        return commentService.getCommentList(requestDto);
    }

    // 댓글 수정하기
    @PutMapping("/api/comment/{commentid}")
    public String updateComment(@PathVariable long commentid, @RequestBody CommentRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(commentid,requestDto,userDetails);
    }

    // 댓글 삭제하기
    @DeleteMapping("/api/comment/{commentid}")
    public String deleteComment(@PathVariable long commentid, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteComment(commentid,userDetails);
    }
}
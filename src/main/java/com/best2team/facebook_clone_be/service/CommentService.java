package com.best2team.facebook_clone_be.service;


import com.best2team.facebook_clone_be.dto.CommentListDto;
import com.best2team.facebook_clone_be.dto.CommentRequestDto;
import com.best2team.facebook_clone_be.dto.CommentResponseDto;
import com.best2team.facebook_clone_be.dto.MsgResponseDto;
import com.best2team.facebook_clone_be.model.Comment;
import com.best2team.facebook_clone_be.model.Post;
import com.best2team.facebook_clone_be.model.User;
import com.best2team.facebook_clone_be.repository.CommentRepository;
import com.best2team.facebook_clone_be.repository.PostRepository;
import com.best2team.facebook_clone_be.repository.UserRepository;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    //댓글 작성
    @Transactional
    public MsgResponseDto createComment(CommentRequestDto requestDto, UserDetailsImpl userDetails) {

        String msg = "댓글이 등록 되었습니다.";
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(IllegalArgumentException::new);

        try {
            Validator.emptyComment(requestDto);
        } catch (IllegalArgumentException e) {
            msg = e.getMessage();
            return new MsgResponseDto(msg);
        }
        String content = requestDto.getComment();

        Comment comment = new Comment(content, userDetails.getUser().getUserId(), post);
        commentRepository.save(comment);

        return new MsgResponseDto(msg);

    }

    //댓글 수정
    @Transactional
    public MsgResponseDto updateComment(long commentid, CommentRequestDto requestDto, UserDetailsImpl userDetails) {

        String msg = "댓글 수정이 완료되었습니다.";
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException("댓글이 존재하지 않습니다!")
        );
        try {
            Validator.sameComment(requestDto);

        } catch (IllegalArgumentException e) {
            msg = e.getMessage();
            return new MsgResponseDto(msg);
        }
        comment.update(requestDto);

        return new MsgResponseDto(msg);
    }

    //댓글 삭제
    public MsgResponseDto deleteComment(long commentid, UserDetailsImpl userDetails) {

        String msg = "댓글 삭제가 완료되었습니다.";
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.!")
        );
        if(!Objects.equals(comment.getUserId(), userDetails.getId())){

            throw new IllegalArgumentException("댓글 삭제가 실패하였습니다.");
        }

        commentRepository.delete(comment);
        return new MsgResponseDto(msg);
    }

//    @Transactional
//    public List<CommentResponseDto> getCommentList(Long postid) {
//        List<Comment> commentList = commentRepository.findAllByPost(postRepository.findById(postid).orElseThrow(IllegalArgumentException::new));
//        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//
//        for(Comment comment:commentList){
//            Long postId = comment.getPost().getPostId();
//            String content = comment.getContent();
//            User user = userRepository.findById(comment.getUserId()).orElseThrow(
//                    ()->new IllegalArgumentException("유저가 존재 하지 않습니다!")
//            );
//            String userName = user.getUserName();
//            Long userId = user.getUserId();
//            LocalDateTime createdAt = comment.getCreatedAt();
//            CommentResponseDto commentResponseDto = new CommentResponseDto(postId,content,userName,userId,createdAt);
//            commentResponseDtoList.add(commentResponseDto);
//        }
//        return commentResponseDtoList;
//    }

//    @Transactional
//    public Page<CommentListDto> getCommentList(Long postid) {
//        List<Comment> commentList = commentRepository.findAllByPost(postRepository.findById(postid).orElseThrow(IllegalArgumentException::new));
//        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//
//        for(Comment comment:commentList){
//            Long postId = comment.getPost().getPostId();
//            String content = comment.getContent();
//            User user = userRepository.findById(comment.getUserId()).orElseThrow(
//                    ()->new IllegalArgumentException("유저가 존재 하지 않습니다!")
//            );
//            String userName = user.getUserName();
//            Long userId = user.getUserId();
//            LocalDateTime createdAt = comment.getCreatedAt();
//            CommentResponseDto commentResponseDto = new CommentResponseDto(postId,content,userName,userId,createdAt);
//            commentResponseDtoList.add(commentResponseDto);
//        }
//        Long totalPage = 1L;
//        return (Page<CommentListDto>) new CommentListDto(commentResponseDtoList,totalPage);
//    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String nullex(IllegalArgumentException e) {
        return e.getMessage();
    }
}

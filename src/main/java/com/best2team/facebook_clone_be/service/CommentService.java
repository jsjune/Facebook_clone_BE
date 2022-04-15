package com.best2team.facebook_clone_be.service;


import com.best2team.facebook_clone_be.dto.CommentRequestDto;
import com.best2team.facebook_clone_be.dto.CommentResponseDto;
import com.best2team.facebook_clone_be.model.Comment;
import com.best2team.facebook_clone_be.model.User;
import com.best2team.facebook_clone_be.repository.CommentRepository;
import com.best2team.facebook_clone_be.repository.PostRepository;
import com.best2team.facebook_clone_be.repository.UserRepository;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public String createComment(CommentRequestDto requestDto,  UserDetailsImpl userDetails) {

        String msg = "댓글이 등록 되었습니다.";
        Long postId = requestDto.getPostId();

        if(!postRepository.existsById(postId)){
            msg = "댓글 작성에 실패하였습니다.";
            throw new IllegalArgumentException(msg);
        }

        String content = requestDto.getComment();

        Comment comment = new Comment(postId, content, userDetails.getUser());
        commentRepository.save(comment);

        return msg;
    }


    //댓글 수정
    @Transactional
    public String updateComment(long commentid, CommentRequestDto requestDto, UserDetailsImpl userDetails) {

        String msg = "댓글 수정이 완료되었습니다.";
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                () -> new IllegalArgumentException()
        );

        if(!Objects.equals(comment.getUserId(), userDetails.getId())){
            msg = "댓글 수정에 실패하였습니다.";
            throw new IllegalArgumentException(msg);
        }
        comment.update(requestDto);

        return msg;
    }

    //댓글 삭제
    public String deleteComment(long commentid, UserDetailsImpl userDetails) {

        String msg = "댓글 삭제가 완료되었습니다.";
        Comment comment = commentRepository.findById(commentid).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.!")
        );
        if(!Objects.equals(comment.getUserId(), userDetails.getId())){
            msg = "댓글 삭제가 실패하였습니다.";
            throw new IllegalArgumentException(msg);
        }

        commentRepository.delete(comment);
        return msg;
    }

    @Transactional
    public List<CommentResponseDto> getCommentList(long postid) {
        List<Comment> commentList = commentRepository.findAllByPostId(postid);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

        for(Comment comment:commentList){
            Long postId = comment.getPostId();
            String content = comment.getContent();
            User user = userRepository.findById(comment.getUserId()).orElseThrow(
                    ()->new IllegalArgumentException()
            );
            String userName = user.getUserName();
            Long userId = user.getUserId();
            LocalDateTime createdAt = comment.getCreatedAt();
            CommentResponseDto commentResponseDto = new CommentResponseDto(postId,content,userName,userId,createdAt);
            commentResponseDtoList.add(commentResponseDto);
        }
        return commentResponseDtoList;
    }
}

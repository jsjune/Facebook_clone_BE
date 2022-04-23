package com.best2team.facebook_clone_be.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private Long postId;
    private String content;
    private String userName;
    private Long userId;
    private LocalDateTime createdAt;


    public CommentResponseDto(Long postId,Long commentId, String content, String userName, Long userId, LocalDateTime createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.userName = userName;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}

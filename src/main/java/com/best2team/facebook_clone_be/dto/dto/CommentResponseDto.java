package com.best2team.facebook_clone_be.dto.dto;

import com.best2team.facebook_clone_be.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long postId;
    private String content;
    private String userName;
    private Long userId;
    private LocalDateTime createAt;


    public CommentResponseDto(Long postId, String content, String userName, Long userId, LocalDateTime createAt) {
        this.postId = postId;
        this.content = content;
        this.userName = userName;
        this.userId = userId;
        this.createAt = createAt;
    }
}

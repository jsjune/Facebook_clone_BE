package com.best2team.facebook_clone_be.dto.dto;

import com.best2team.facebook_clone_be.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentResponseDto {
    private Long postId;
    private String content;
    private String userName;
    private Long userId;
    private Date createAt;

    public CommentResponseDto(Long postId, String content, User user) {
        this.postId = postId;
        this.content = content;
        this.userName = user.getUserName();
        this.userId = getUserId();
    }
}

package com.best2team.facebook_clone_be.dto;

import java.time.LocalDateTime;

public class PostDto {
    private Long postId;
    private String content;
    private Long likeCnt;
    private Long commentCnt;
    private LocalDateTime createAt;
    private String userImageUrl;
    private String postImageUrl;
    private String userName;
    private Long userId;
    private boolean like;

    public PostDto(Long postId, String content, Long likeCnt, Long commentCnt, LocalDateTime createAt, String userImageUrl, String postImageUrl, String userName, Long userId, boolean like) {
        this.postId = postId;
        this.content = content;
        this.likeCnt = likeCnt;
        this.commentCnt = commentCnt;
        this.createAt = createAt;
        this.userImageUrl = userImageUrl;
        this.postImageUrl = postImageUrl;
        this.userName = userName;
        this.userId = userId;
        this.like = like;
    }
}

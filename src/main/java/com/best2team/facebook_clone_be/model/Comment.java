package com.best2team.facebook_clone_be.model;

import com.best2team.facebook_clone_be.dto.CommentRequestDto;
import com.best2team.facebook_clone_be.utils.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_comment")
public class Comment extends Timestamped {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;




    public Comment(Long postId, String content, User user) {
        this.content = content;
        this.userId = user.getUserId();
        this.postId = postId;
    }

    public Comment(Long postId, String content, String userName) {
        this.content = content;
        this.postId = postId;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getComment();
    }
}

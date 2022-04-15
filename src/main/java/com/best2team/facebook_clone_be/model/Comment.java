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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId")
    private Post post;


    public Comment(String content, Long userId, Post post) {
        this.content = content;
        this.userId = userId;
        this.post = post;
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getComment();
    }
}

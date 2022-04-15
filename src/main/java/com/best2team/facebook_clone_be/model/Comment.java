package com.best2team.facebook_clone_be.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_comment")
public class Comment {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long commentId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, unique = true)
    private String postImageUrl;
}

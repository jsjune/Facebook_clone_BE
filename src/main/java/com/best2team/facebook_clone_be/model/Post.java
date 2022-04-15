package com.best2team.facebook_clone_be.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_post")
public class Post {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long postId;

    @OneToOne
    @JoinColumn(name = "postImageId")
    private PostImage postImage;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, unique = true)
    private Long userId;

    public Post(String content, Long userId) {
        this.content=content;
        this.userId=userId;
    }

    public void update(PostImage postImage){
        this.postImage = postImage;
    }
}

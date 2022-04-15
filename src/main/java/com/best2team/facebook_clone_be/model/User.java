package com.best2team.facebook_clone_be.model;

import com.best2team.facebook_clone_be.dto.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long userId;

    @OneToOne
    @JoinColumn(name = "userImageId")
    private UserImage userImage;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "postId")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "commentId")
    private List<Comment> commentList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isLogin;

    public User(SignupRequestDto requestDto){
        this.userEmail = requestDto.getUserEmail();
        this.userName = requestDto.getUserName();
        this.password = requestDto.getPassword();
    }

}

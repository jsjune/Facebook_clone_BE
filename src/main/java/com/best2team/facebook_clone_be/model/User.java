package com.best2team.facebook_clone_be.model;

import com.best2team.facebook_clone_be.dto.SignupRequestDto;
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

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
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

    public void update(UserImage userImage) {
        this.userImage = userImage;
    }

}

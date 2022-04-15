package com.best2team.facebook_clone_be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String userName;
    private String userEmail;
    private String password;
    private String userImage;
}

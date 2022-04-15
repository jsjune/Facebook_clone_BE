package com.best2team.facebook_clone_be.controller;

import com.best2team.facebook_clone_be.dto.SignupRequestDto;
import com.best2team.facebook_clone_be.dto.UserResponseDto;

import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService){
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }


    @PostMapping("/api/user/islogin")
    public UserResponseDto userIsLogin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new UserResponseDto(userDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object nullex(IllegalArgumentException e) {
        return e.getMessage();
    }
}

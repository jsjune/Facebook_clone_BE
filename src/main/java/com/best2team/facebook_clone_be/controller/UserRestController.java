package com.best2team.facebook_clone_be.controller;

import com.best2team.facebook_clone_be.dto.ProfileResponseDto;
import com.best2team.facebook_clone_be.dto.SignupRequestDto;
import com.best2team.facebook_clone_be.dto.UserResponseDto;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.service.UserService;
import com.best2team.facebook_clone_be.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UserRestController {
    private final UserService userService;

    // 회원가입
    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto){

        return userService.signup(signupRequestDto);
    }

    @PostMapping("/api/user/islogin")
    public UserResponseDto isLogin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return new UserResponseDto(userDetails);
    }

    @PostMapping("/api/user/image")
    public ProfileResponseDto registImage(@RequestParam("image") MultipartFile file, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException{
        return userService.registImage(file, userDetails);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Object nullex(IllegalArgumentException e) {
        return e.getMessage();
    }
}

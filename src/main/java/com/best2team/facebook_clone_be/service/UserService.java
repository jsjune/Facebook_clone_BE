package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.dto.ProfileResponseDto;
import com.best2team.facebook_clone_be.dto.SignupRequestDto;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    String signup(SignupRequestDto signupRequestDto);


    ProfileResponseDto registImage(MultipartFile file, UserDetailsImpl userDetails)throws IOException;
}

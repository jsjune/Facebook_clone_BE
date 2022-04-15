package com.best2team.facebook_clone_be.service;


import com.best2team.facebook_clone_be.dto.SignupRequestDto;
import com.best2team.facebook_clone_be.model.User;
import com.best2team.facebook_clone_be.repository.UserRepository;
import com.best2team.facebook_clone_be.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Validator validator;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, Validator validator, PasswordEncoder encoder){
        this.userRepository = userRepository;
        this.validator = validator;
        this.encoder = encoder;
    }

    // 회원가입
    public String signup(SignupRequestDto signupRequestDto) {
        String msg = "회원 가입이 완료되었습니다.";

        try {
            // 회원가입 검증
            validator.signupValidate(signupRequestDto);

        }catch (IllegalArgumentException e){
            msg = e.getMessage();
            return msg;
        }

        signupRequestDto.setPassword(encoder.encode(signupRequestDto.getPassword()));
        userRepository.save(new User(signupRequestDto));
        return msg;
    }

}

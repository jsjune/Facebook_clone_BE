package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.dto.ImageDto;
import com.best2team.facebook_clone_be.dto.MsgResponseDto;
import com.best2team.facebook_clone_be.model.Post;
import com.best2team.facebook_clone_be.model.PostImage;
import com.best2team.facebook_clone_be.model.User;
import com.best2team.facebook_clone_be.repository.PostImageRepository;
import com.best2team.facebook_clone_be.repository.PostRepository;
import com.best2team.facebook_clone_be.repository.UserRepository;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.utils.S3.S3Uploader;
import com.best2team.facebook_clone_be.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final S3Uploader s3Uploader;
    private final PostRepository postRepository;
    private final PostImageRepository imageRepository;
    private final Validator validator;
    private final UserRepository userRepository;

    @Transactional
    public MsgResponseDto writePost(UserDetailsImpl userDetails, MultipartFile multipartFile, String content) throws IOException {

        validator.sameContent(content==null,"내용을 입력하세요");
        User user =userRepository.findById(userDetails.getUser().getUserId()).orElseThrow(IllegalArgumentException::new);

        PostImage postImage = new PostImage(s3Uploader.upload(multipartFile, "static"));

        Post post = new Post(content, user, imageRepository.save(postImage));

        postRepository.save(post);

        String msg="게시글 작성이 성공되었습니다.";
        return new MsgResponseDto(msg);


    }
}

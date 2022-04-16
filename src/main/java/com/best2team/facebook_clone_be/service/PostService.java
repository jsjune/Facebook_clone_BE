package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.dto.MsgResponseDto;
import com.best2team.facebook_clone_be.dto.PostListDto;
import com.best2team.facebook_clone_be.dto.PostResponseDto;
import com.best2team.facebook_clone_be.model.Comment;
import com.best2team.facebook_clone_be.model.Post;
import com.best2team.facebook_clone_be.model.PostImage;
import com.best2team.facebook_clone_be.model.User;
import com.best2team.facebook_clone_be.repository.*;
import com.best2team.facebook_clone_be.security.UserDetailsImpl;
import com.best2team.facebook_clone_be.utils.S3.S3Uploader;
import com.best2team.facebook_clone_be.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final S3Uploader s3Uploader;
    private final PostRepository postRepository;
    private final PostImageRepository imageRepository;
    private final Validator validator;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public MsgResponseDto writePost(UserDetailsImpl userDetails, MultipartFile multipartFile, String content) throws IOException {
        try {
            validator.sameContent(content == null, "내용을 입력하세요");
            User user = userRepository.findById(userDetails.getUser().getUserId()).orElse(null);
            PostImage postImage = new PostImage(s3Uploader.upload(multipartFile, "static"));
            Post post = new Post(content, user, imageRepository.save(postImage));
            postRepository.save(post);
            String msg = "게시글 작성이 성공되었습니다.";
            return new MsgResponseDto(msg);
        } catch (Exception e) {
            String msg = "게시글 작성 XXX";
            return new MsgResponseDto(msg);
        }
    }


    public PostResponseDto showAllPost(int postno,UserDetailsImpl userDetails) {
        List<Post> postList = postRepository.findAllByUser(userDetails.getUser());
        List<PostListDto> postListDto = new ArrayList<>();
        for (Post post : postList) {
            Long userId = userDetails.getUser().getUserId();
            Long postId = post.getPostId();
            String content = post.getContent();
            Long likeCnt = likeRepository.countAllByPostId(postId);
            List<Comment> commentList = commentRepository.findAllByPost(post);
            Long commentCnt = Long.valueOf(commentList.size());
            LocalDateTime createAt = post.getCreatedAt();
            String postImageUrl = post.getPostImage().getPostImageUrl();
            boolean like= likeRepository.findByPostIdAndUserId(post.getPostId(),userDetails.getUser().getUserId()).isPresent();

//            String userImageUrl = userDetails.getUser().getUserImage().getImageUrl();
            String userImageUrl = post.getPostImage().getPostImageUrl(); // 테스트용
            String userName = userDetails.getUser().getUserName();
            PostListDto result = new PostListDto(postId,content,likeCnt,commentCnt,createAt,userImageUrl,postImageUrl,userName,userId,like);
            postListDto.add(result);
        }
        Pageable pageable = getPageable(postno);
        int start = postno * 10;
        int end = Math.min((start + 10), postListDto.size());


        int totalPage;
        if (postListDto.size() % 10 == 0) {
            totalPage = postListDto.size() / 10;
        } else {
            totalPage = postListDto.size() / 10+1;
        }
        return new PostResponseDto(postListDto, totalPage);
    }

    private Pageable getPageable(int page) {
        Sort.Direction direction = Sort.Direction.DESC ;
        Sort sort = Sort.by(direction, "id");
        return PageRequest.of(page, 10,sort);
    }


    public MsgResponseDto deletePost(Long postid) {
        postRepository.deleteAllByPostId(postid);
        return new MsgResponseDto("게시글 삭제가 완료되었습니다");
    }
}

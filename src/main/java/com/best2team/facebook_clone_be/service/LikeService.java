package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.dto.LikeDto;
import com.best2team.facebook_clone_be.model.Like;
import com.best2team.facebook_clone_be.repository.LikeRepository;
import com.best2team.facebook_clone_be.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository favoriteRepository;
    private final Validator validator;

    //좋아요
    public String like(LikeDto likeDto){
        Like like = new Like(likeDto);
        validator.alreadyDelete(favoriteRepository.findByPostIdAndUserId(likeDto.getPostId(), likeDto.getUserId()).isPresent(), "좋아요가 이미 눌러진 상태입니다.");
        favoriteRepository.save(like);
        return "좋아요";
    }

    //좋아요 취소
    public String unLike(Long postid, Long userid){
        Optional<Like> like = favoriteRepository.findByPostIdAndUserId(postid,userid);
        validator.alreadyDelete(!like.isPresent(), "이미 좋아요가 취소된 상태입니다.");
        favoriteRepository.deleteById(like.orElseThrow(IllegalArgumentException::new).getLikeId());
        return "좋아요";
    }

}

package com.best2team.facebook_clone_be.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDto {
    private List<PostDto> postList;
    private int totalPage;

    public PostResponseDto(List<PostDto> postDtoList, int i) {
        this.postList=postDtoList;
        this.totalPage= i;
    }
}

package com.best2team.facebook_clone_be.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommentListDto {
    private List<CommentResponseDto> comments;
    private Long totalPage;

    public CommentListDto(List<CommentResponseDto> commentResponseDtoList, Long totalPage) {
        this.comments = commentResponseDtoList;
        this.totalPage = totalPage;
    }
}

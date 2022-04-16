package com.best2team.facebook_clone_be.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MsgResponseDto {
    private String msg;

    public MsgResponseDto(String msg) {
        this.msg = msg;
    }
}

package com.best2team.facebook_clone_be.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class ImageDto {
    private String imageUrl;
    private String fileName;

    public ImageDto(String imageUrl, String fileName) {
        this.imageUrl = imageUrl;
        this.fileName = fileName;
    }
}

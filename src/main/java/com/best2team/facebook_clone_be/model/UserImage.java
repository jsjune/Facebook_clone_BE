package com.best2team.facebook_clone_be.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_user_image")
public class UserImage {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long userImageId;

    @Column(nullable = false)
    private String imageUrl;
}

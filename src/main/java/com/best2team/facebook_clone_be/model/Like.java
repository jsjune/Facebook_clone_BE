package com.best2team.facebook_clone_be.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tbl_like")
public class Like {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long likeId;

    @Column
    private Long userId;

    @Column
    private Long postId;

}

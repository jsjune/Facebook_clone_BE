package com.best2team.facebook_clone_be.service;

import com.best2team.facebook_clone_be.security.UserDetailsImpl;

public interface LikeService {
    String like(Long postid, UserDetailsImpl userDetails);
}

package com.best2team.facebook_clone_be.repository;

import com.best2team.facebook_clone_be.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

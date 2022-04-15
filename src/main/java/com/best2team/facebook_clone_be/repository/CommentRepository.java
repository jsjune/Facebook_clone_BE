package com.best2team.facebook_clone_be.repository;

import com.best2team.facebook_clone_be.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}

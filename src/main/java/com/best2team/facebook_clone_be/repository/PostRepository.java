package com.best2team.facebook_clone_be.repository;


import com.best2team.facebook_clone_be.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Override
    boolean existsById(Long aLong);
}

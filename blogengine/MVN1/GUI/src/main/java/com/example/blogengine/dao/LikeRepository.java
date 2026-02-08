package com.example.blogengine.dao;

import com.example.blogengine.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    long countByBlog_Id(Long blogId);
    boolean existsByBlog_IdAndUsername(Long blogId, String username);
    void deleteByBlog_IdAndUsername(Long blogId, String username);
}

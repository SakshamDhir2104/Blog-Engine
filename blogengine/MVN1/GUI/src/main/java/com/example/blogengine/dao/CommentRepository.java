package com.example.blogengine.dao;

import com.example.blogengine.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlog_IdOrderByCreatedAtAsc(Long blogId);
    long countByBlog_Id(Long blogId);
}

package com.example.blogengine.service;

import com.example.blogengine.dao.BlogRepository;
import com.example.blogengine.dao.CommentRepository;
import com.example.blogengine.model.Blog;
import com.example.blogengine.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    public List<Comment> listByBlog(Long blogId) {
        return commentRepository.findByBlog_IdOrderByCreatedAtAsc(blogId);
    }

    public Comment addComment(Long blogId, String author, String content) {
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Blog not found"));
        Comment c = new Comment(blog, author, content);
        return commentRepository.save(c);
    }

    public long countByBlog(Long blogId) {
        return commentRepository.countByBlog_Id(blogId);
    }
}

package com.example.blogengine.service;

import com.example.blogengine.dao.BlogRepository;
import com.example.blogengine.dao.LikeRepository;
import com.example.blogengine.model.Blog;
import com.example.blogengine.model.Like;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final BlogRepository blogRepository;

    public LikeService(LikeRepository likeRepository, BlogRepository blogRepository) {
        this.likeRepository = likeRepository;
        this.blogRepository = blogRepository;
    }

    public long count(Long blogId) {
        return likeRepository.countByBlog_Id(blogId);
    }

    public boolean hasUserLiked(Long blogId, String username) {
        if (username == null) return false;
        return likeRepository.existsByBlog_IdAndUsername(blogId, username);
    }

    public void like(Long blogId, String username) {
        if (username == null || username.isBlank()) return;
        if (likeRepository.existsByBlog_IdAndUsername(blogId, username)) return;
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new IllegalArgumentException("Blog not found"));
        likeRepository.save(new Like(blog, username));
    }

    public void unlike(Long blogId, String username) {
        if (username == null || username.isBlank()) return;
        likeRepository.deleteByBlog_IdAndUsername(blogId, username);
    }

    public void toggle(Long blogId, String username) {
        if (hasUserLiked(blogId, username)) {
            unlike(blogId, username);
        } else {
            like(blogId, username);
        }
    }
}

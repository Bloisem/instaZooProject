package com.example.demo.repository;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByUserId(Long userId);
    Comment findByUserId(Long commentId, Long userId);
}

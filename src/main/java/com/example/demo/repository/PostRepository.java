package com.example.demo.repository;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllPostByUserOrderByCreatedDateDesc(User user);

    List<Post> findAllOrderByCreatedDateDesc();
    Optional<Post> findPostByIdAndUser(Long id, User user);
}

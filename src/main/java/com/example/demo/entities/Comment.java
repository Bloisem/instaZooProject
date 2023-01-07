package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private Long userId;
    @Column(columnDefinition = "text", nullable = false)
    private String message;
    private int likes;
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @PrePersist
    protected void onCreated(){
        this.createdDate= LocalDateTime.now();
    }
}

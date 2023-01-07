package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String caption;
    private String location;
    private int likes;
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @Column
    @ElementCollection(targetClass = String.class)
    private Set<String> likedUsers= new HashSet<>();
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true )
    private List<Comment> comments= new ArrayList<>();

    @PrePersist
    protected void onCreated(){
        this.createdDate= LocalDateTime.now();
    }
}

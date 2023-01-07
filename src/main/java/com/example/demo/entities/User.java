package com.example.demo.entities;

import com.example.demo.entities.enums.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)//can't save without name
    private String name;
    @Column(nullable = false)
    private String lastname;
    @Column(unique = true,updatable = false) //can't update username
    private String username;
    @Column(unique = true)
    private String email;
    @Column(columnDefinition = "text")
    private String bio;
    @Column(length = 3000)
    private String password;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;
    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name= "user_role",
    joinColumns=@JoinColumn(name = "user_id"))//create table with 2 collumns userId+userRole
    private Set<ERole> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true) //cascade to delete all user's posts
    private List<Post> posts = new ArrayList<>();
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @PrePersist
    protected void onCreated(){
        this.createdDate= LocalDateTime.now();
    }
    /***SECURITY****/
    @Override
    public String getPassword(){
        return password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.example.week7TASK.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="likes")
public class PostLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postLikeId;


    @ManyToOne
    private Posts posts;

    @ManyToOne
    private UserInfo userInfo;
}

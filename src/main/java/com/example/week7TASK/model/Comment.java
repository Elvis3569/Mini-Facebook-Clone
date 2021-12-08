package com.example.week7TASK.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String commentBody;

    @ManyToOne
    private Posts posts;


    @ManyToOne
    private UserInfo userInfo;

    private String comments;

    public Comment(String comment, Posts posts, UserInfo userInfo) {
        this.commentBody = comment;
        this.posts = posts;
        this.userInfo = userInfo;
    }

    public Comment(Long commentId, String comment) {
        this.commentId = commentId;
        this.commentBody = comment;
    }
}

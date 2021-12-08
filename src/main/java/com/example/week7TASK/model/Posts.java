package com.example.week7TASK.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private UserInfo userInfo;

    @OneToMany
    private List<Comment> listOfComments = new ArrayList<>();

    @OneToMany
    private List<PostLikes> postLikes = new ArrayList<>();
}

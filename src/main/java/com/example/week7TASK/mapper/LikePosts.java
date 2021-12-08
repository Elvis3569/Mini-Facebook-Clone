package com.example.week7TASK.mapper;

import com.example.week7TASK.model.Comment;
import com.example.week7TASK.model.PostLikes;
import com.example.week7TASK.model.UserInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LikePosts {
    private Long postId;
    private String title;
    private String body;
    private UserInfo userInfo;
    private List<Comment> listOfComments = new ArrayList<>();
    private List<PostLikes> postLikes = new ArrayList<>();
    private boolean likedPost;

    public boolean getLikedPost() {
        return likedPost;
    }

    public void setLikedPost(boolean likedPost) {
        this.likedPost = likedPost;
    }

}

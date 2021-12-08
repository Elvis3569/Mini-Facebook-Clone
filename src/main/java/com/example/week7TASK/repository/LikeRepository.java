package com.example.week7TASK.repository;

import com.example.week7TASK.model.PostLikes;
import com.example.week7TASK.model.Posts;
import com.example.week7TASK.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<PostLikes, Long> {

    @Transactional
    void deletePostsLikesByPostsAndUserInfo(Posts posts, UserInfo userInfo);

    List<PostLikes> findAllByPosts(Posts posts);

    void deleteAllByPosts(Posts posts);
}

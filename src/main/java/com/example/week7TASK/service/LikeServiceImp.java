package com.example.week7TASK.service;

import com.example.week7TASK.model.PostLikes;
import com.example.week7TASK.model.Posts;
import com.example.week7TASK.model.UserInfo;
import com.example.week7TASK.repository.LikeRepository;
import com.example.week7TASK.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImp {

    private final LikeRepository likeRepository;

    private final PostRepository postRepository;

    public LikeServiceImp(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }


    public boolean likePost(UserInfo userInfo, Long postId, String action){
        boolean result = false;
        Posts posts = postRepository.findByPostId(postId);
        try{
            PostLikes like = new PostLikes();
            like.setUserInfo(userInfo);
            like.setPosts(posts);
            if(action.equals("1")){
                likeRepository.save(like);
                System.out.println("save");
            }else{
                System.out.println("delete");
                likeRepository.deletePostsLikesByPostsAndUserInfo(posts,userInfo);
            }
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Transactional
    public void deleteAllLikesInPost(Posts posts) {
        likeRepository.deleteAllByPosts(posts);
    }
}

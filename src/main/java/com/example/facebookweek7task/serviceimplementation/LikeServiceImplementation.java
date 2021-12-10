package com.example.facebookweek7task.serviceimplementation;

import com.example.facebookweek7task.entity.Post;
import com.example.facebookweek7task.entity.PostLikes;
import com.example.facebookweek7task.entity.User;
import com.example.facebookweek7task.repositories.LikeRepository;
import com.example.facebookweek7task.repositories.PostRepository;
import com.example.facebookweek7task.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeServiceImplementation implements LikeService {

    final
    LikeRepository likeRepository;
    final
    PostRepository postRepository;

    public LikeServiceImplementation(LikeRepository likeRepository, PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    /**
     * Method to like and unlike a post
     *   the user performing the action
     *   the id of the post
     *   the action performed
     *
     */
    @Override
    public boolean likePost(User user, Long postId, String action){
        boolean result = false;
        Post post = postRepository.findByPostId(postId);
        try{
            PostLikes like = new PostLikes();
            like.setUser(user);
            like.setPost(post);
            if(action.equals("1")){
                likeRepository.save(like);
                System.out.println("save");
            }else{
                System.out.println("delete");
                likeRepository.deletePostLikesByPostAndUser(post,user);
            }
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Method to delete all the likes in a post
     *  post the post with the likes to be deleted
     */
    @Override
    @Transactional
    public void deleteAllLikesInPost(Post post) {
        likeRepository.deleteAllByPost(post);
    }
}

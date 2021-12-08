package com.example.week7TASK.service;

import com.example.week7TASK.mapper.LikePosts;
import com.example.week7TASK.model.Comment;
import com.example.week7TASK.model.PostLikes;
import com.example.week7TASK.model.Posts;
import com.example.week7TASK.model.UserInfo;
import com.example.week7TASK.repository.CommentRepository;
import com.example.week7TASK.repository.LikeRepository;
import com.example.week7TASK.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImp {


    private final PostRepository postRepository;


    private final LikeRepository likeRepository;


    public final CommentRepository commentRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository, LikeRepository likeRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
    }

    public void addPost(UserInfo userInfo, Posts posts) {
        posts.setUserInfo(userInfo);
        postRepository.save(posts);
    }


    public List<LikePosts> getAllPost(UserInfo userInfo) {
        List<LikePosts> listOfLikePosts = new ArrayList<>();
        List<Posts> listOfPosts = postRepository.findAllByPostIdIsNotNullOrderByPostIdDesc();

        for (Posts posts : listOfPosts) {

            LikePosts likePosts = new LikePosts();

            likePosts.setPostId(posts.getPostId());
//            likePosts.setTitle(posts.getTitle());
            likePosts.setBody(posts.getContent());
            likePosts.setUserInfo(posts.getUserInfo());

            List<PostLikes> listOfLikes =  likeRepository.findAllByPosts(posts);
            List<Comment> listOfComments = commentRepository.findCommentByPosts(posts);
            likePosts.setListOfComments(listOfComments);



            System.out.println("lllllllll" + listOfComments);
            likePosts.setPostLikes(listOfLikes);


            List<PostLikes> listOfPostLikes = likeRepository.findAllByPosts(posts);

            for (PostLikes like: listOfPostLikes) {
                System.out.println("wwwwww "+like.getUserInfo().getId() );
                System.out.println("uuuuuuu "+userInfo.getId());
                if (like.getUserInfo().getId().equals(userInfo.getId())) {
                    likePosts.setLikedPost(true);
                    break;
                }

            }

            System.out.println("tttttttt"+listOfPostLikes);

            listOfLikePosts.add(likePosts);
        }
        return listOfLikePosts;
    }


    public void updatePost(Posts posts) {
        postRepository.save(posts);
    }



    public void deletePost(Posts posts) {
        postRepository.delete(posts);
    }


    public Posts getPostById(Long id) {
        return postRepository.findByPostId(id);
    }


}

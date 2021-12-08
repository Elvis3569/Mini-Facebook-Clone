package com.example.week7TASK.service;

import com.example.week7TASK.model.Comment;
import com.example.week7TASK.model.Posts;
import com.example.week7TASK.repository.CommentRepository;
import com.example.week7TASK.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImp {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }


    public List<Comment> findCommentByPost(Posts posts) {
        return commentRepository.findCommentByPosts(posts);
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteCommentByCommentId(commentId);
    }



    @Transactional
    public void deleteAllCommentsInPost(Posts posts) {
        commentRepository.deleteAllByPosts(posts);
    }


    public void updateComment(Comment editedComment) {
        commentRepository.save(editedComment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findByCommentId(id);
    }



}

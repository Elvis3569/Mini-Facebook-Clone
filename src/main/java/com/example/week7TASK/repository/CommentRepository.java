package com.example.week7TASK.repository;

import com.example.week7TASK.model.Comment;
import com.example.week7TASK.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByCommentId(Long id);

    List<Comment> findCommentByPosts(Posts posts);

    void deleteCommentByCommentId(Long commentId);

    void deleteAllByPosts(Posts posts);
}

package com.example.week7TASK.repository;

import com.example.week7TASK.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Posts, Long> {
    List<Posts> findAllByPostIdIsNotNullOrderByPostIdDesc();
    Posts findByPostId(Long id);
}

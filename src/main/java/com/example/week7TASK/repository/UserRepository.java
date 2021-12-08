package com.example.week7TASK.repository;

import com.example.week7TASK.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByEmailAndPassword(String email, String password);
//    Optional<UserInfo> findFirstByLogin(String email);
}

package com.example.week7TASK.service;

import com.example.week7TASK.model.Posts;
import com.example.week7TASK.model.UserInfo;
import com.example.week7TASK.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo registerUser(String firstName, String lastName, String email, String password) {
        if(email == null && password == null){
            return null;
        }else{
//            if(userRepository.findFirstByLogin(email).isPresent()){
//                System.out.println("Duplicate login");
//                return null;
//            }
            UserInfo userInfo = new UserInfo();
            userInfo.setFirstName(firstName);
            userInfo.setLastName(lastName);
            userInfo.setEmail(email);
            userInfo.setPassword(password);
            return userRepository.save(userInfo);
        }
    }

    public UserInfo authentication(String email, String password) {
        return userRepository.findByEmailAndPassword(email,password).orElse(null);
    }


}

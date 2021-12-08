package com.example.week7TASK.controller;

import com.example.week7TASK.model.UserInfo;
import com.example.week7TASK.service.PostServiceImp;
import com.example.week7TASK.service.UserServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final PostServiceImp postServiceImp;


    private final UserServiceImplementation userServiceImplementation;


    public UserController(PostServiceImp postServiceImp, UserServiceImplementation userServiceImplementation) {
        this.postServiceImp = postServiceImp;
        this.userServiceImplementation = userServiceImplementation;
    }


    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new UserInfo());
//        return "register_page";
        return "Signup&Login";

    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserInfo());
//        return "login_page";
        return "Signup&Login";

    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserInfo userInfo) {
        System.out.println("register request: " + userInfo);
        UserInfo registeredUser = userServiceImplementation.registerUser(userInfo.getFirstName(),userInfo.getLastName(),userInfo.getEmail(), userInfo.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserInfo userInfo, Model model){
        System.out.println("login request: " + userInfo);
        UserInfo authenticated = userServiceImplementation.authentication(userInfo.getEmail(), userInfo.getPassword());
//        return registeredUser == null ? "error_page" : "redirect/login";

        if(authenticated != null) {
            model.addAttribute("userLogin", authenticated.getEmail());

//            postServiceImp.viewPost(model);
            return "home";
        } else {
            return "error_page";
        }
    }
}

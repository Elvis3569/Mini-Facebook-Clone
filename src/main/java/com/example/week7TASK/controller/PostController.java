package com.example.week7TASK.controller;

import com.example.week7TASK.mapper.LikePosts;
import com.example.week7TASK.model.Comment;
import com.example.week7TASK.model.PostLikes;
import com.example.week7TASK.model.Posts;
import com.example.week7TASK.model.UserInfo;
import com.example.week7TASK.service.CommentServiceImp;
import com.example.week7TASK.service.LikeServiceImp;
import com.example.week7TASK.service.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    private final PostServiceImp postServiceImp;


    private final CommentServiceImp commentServiceImp;


    private final LikeServiceImp likeServiceImp;


    @Autowired
    public PostController(PostServiceImp postServiceImp, CommentServiceImp commentServiceImp, LikeServiceImp likeServiceImp) {
        this.postServiceImp = postServiceImp;
        this.commentServiceImp = commentServiceImp;
        this.likeServiceImp = likeServiceImp;
    }



    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("logUser");
        if (userInfo==null) return "redirect:/";
        model.addAttribute("post", new Posts());
        model.addAttribute("newComment", new Comment());
        model.addAttribute("loggedUser", userInfo);
        model.addAttribute("postLikes", new PostLikes());
        model.addAttribute("postDelete", new Posts());

        List<LikePosts> listOfPosts = postServiceImp.getAllPost(userInfo);

        model.addAttribute("listOfAllPosts", listOfPosts);
        return "home";
    }


    @GetMapping("/updatepost")
    public String getUpdatePostPage(Model model, HttpSession httpSession, Long postId) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("logUser");
        if (userInfo==null) return "redirect:/";

        Posts posts = postServiceImp.getPostById(postId);

        model.addAttribute("editpost", posts);
        model.addAttribute("loggedUser", userInfo);

        return "updatepost";
    }



    @PostMapping("/home_post")
    public String createPost(HttpSession httpSession, @ModelAttribute("post") Posts posts) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("logUser");
        postServiceImp.addPost(userInfo, posts);
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updatePost(HttpSession httpSession, Posts posts){
        Posts newPost = postServiceImp.getPostById(posts.getPostId());
        newPost.setContent(posts.getContent());
        postServiceImp.updatePost(newPost);
        return "redirect:/home";
    }

//    @PostMapping("/delete/{id}")
//    public String deletePost(@PathVariable("id") Long id, HttpSession httpSession, RedirectAttributes redirectAttributes) {
//        UserInfo userInfo = (UserInfo) httpSession.getAttribute("logUser");
//        ResponseDTO response = new ResponseDTO();
//        if (userInfo == null) {
//            return "redirect:/index";
//        }
//        Posts posts = postServiceImp.getPostById(id);
//        commentServiceImp.deleteAllCommentsInPost(posts);
//        likeServiceImp.deleteAllLikesInPost(posts);
//        postServiceImp.deletePost(posts);
//        redirectAttributes.addFlashAttribute("message", response.getMessage());
//        return "redirect:/home";
//    }

}

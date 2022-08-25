package com.ning.news.controller;

import com.ning.news.config.Config;
import com.ning.news.entity.*;
import com.ning.news.payload.response.Response;
import com.ning.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    CommentService commentService;

    //所有新闻展示
    @PostMapping({"/deleteComment/{id}"})
    public Response deleteComment(@PathVariable int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        int delete = commentService.deleteCommentByUserNews(user.getId(),id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    //所有新闻展示
    @PostMapping({"/addComment/{id}"})
    public Response addComment(Model model, HttpSession session, @PathVariable int id, String content){
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment(id, user.getId(), content,new Date());
        int add = commentService.addComment(comment);
        if (add > 0 ){
            return new Response(Config.SUCCESS_CODE,"Add success",null);
        }else{
            return new Response(Config.ERROR_CODE,"Add fail",null);
        }
    }
}

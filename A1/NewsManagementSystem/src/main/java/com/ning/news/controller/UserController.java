package com.ning.news.controller;

import com.ning.news.entity.Category;
import com.ning.news.entity.Comment;
import com.ning.news.entity.News;
import com.ning.news.entity.User;
import com.ning.news.param.UserParam;
import com.ning.news.service.CommentService;
import com.ning.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    CommentService commentService;

    //所有新闻展示
    @RequestMapping({"/deleteComment/{id}"})
    public String deleteComment(@PathVariable int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        int delete = commentService.deleteCommentByUserNews(user.getId(),id);
        return "redirect:/news/getNewsDetailById/"+id;
    }

    //所有新闻展示
    @RequestMapping({"/addComment/{id}"})
    public String addComment(Model model, HttpSession session, @PathVariable int id, String content){
        User user = (User) session.getAttribute("user");
        Comment comment = new Comment(id, user.getId(), content,new Date());
        int add = commentService.addComment(comment);
        return "redirect:/news/getNewsDetailById/"+id;
    }

}

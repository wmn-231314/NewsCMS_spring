package com.ning.controller;

import com.ning.entity.Comment;
import com.ning.entity.User;
import com.ning.payload.response.Response;
import com.ning.service.CommentService;
import com.ning.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/getByNews/{id}")
    public Response getCommentByNews(@PathVariable int id){
        Map<String,Object> data = new HashMap();
        List<Comment> commentList = commentService.getCommentByNews(id);
        data.put("comments",commentList);
        return new Response(Config.SUCCESS_CODE,"get comments success",commentList);
    }

    //所有新闻展示
    @PostMapping({"/deleteComment/{id}"})
    public Response deleteComment(@PathVariable int id){
        int delete = commentService.deleteCommentById(id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    //所有新闻展示
    @PostMapping({"/addComment"})
    public Response addComment(@RequestParam int id,@RequestParam int user_id, String content){
        Comment comment = new Comment(id, user_id, content,new Date());
        int add = commentService.addComment(comment);
        if (add > 0 ){
            return new Response(Config.SUCCESS_CODE,"Add success",null);
        }else{
            return new Response(Config.ERROR_CODE,"Add fail",null);
        }
    }

    @PostMapping("/deleteByUser/{id}")
    public Response deleteByUserId(@PathVariable int id){
        int delete = commentService.deleteCommentByUser(id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    //所有新闻展示
    @PostMapping({"/deleteByNews/{id}"})
    public Response deleteByNews(@PathVariable int id){
        int delete = commentService.deleteCommentByNews(id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }
}

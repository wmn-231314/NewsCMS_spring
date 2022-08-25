package com.ning.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ning.service.UserService;
import com.ning.utils.Config;
import com.ning.entity.*;
import com.ning.payload.response.Response;
//import com.ning.service.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/delete/user")
    @ResponseBody
    @HystrixCommand(fallbackMethod = "onError")
    public Response deleteUser(@RequestParam int id){
        try {
            int delete = userService.deleteUserById(id);
            if(delete > 0){
                return new Response(Config.SUCCESS_CODE,"success",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.ERROR_CODE,"fail",null);
    }

    @GetMapping(value = "/getMemberInfo")
    @ResponseBody
    public Response getMemberInfo() throws Exception {
        List<User> allUser = userService.getUserListByPermission(Config.PRE_TEXT+Config.VIP_USER);
//        Layui l = Layui.data(allUser.size(), allUser,"Success");
        return new Response(Config.SUCCESS_CODE,"success",allUser);
    }

    @GetMapping(value = "/getAdminInfo")
    @ResponseBody
    public Response getAdminInfo() throws Exception {
        List<User> allUser = userService.getUserListByPermission(Config.PRE_TEXT+Config.ADMIN);
//        Layui l = Layui.data(allUser.size(), allUser,"Success");
        return new Response(Config.SUCCESS_CODE,"success",allUser);
    }

    @GetMapping(value = "/getEditorInfo")
    @ResponseBody
    public Response getEditorInfo() throws Exception {
        List<User> allUser = userService.getUserListByPermission(Config.PRE_TEXT+Config.PRIMARY_EDITOR);
        List<User> allReviewer = userService.getUserListByPermission(Config.PRE_TEXT+Config.SENIOR_EDITOR);
        allUser.addAll(allReviewer);
//        Layui l = Layui.data(allUser.size(), allUser,"Success");
        return new Response(Config.SUCCESS_CODE,"success",allUser);
    }

    @PostMapping("/ban/user")
    @ResponseBody
    public Response banUser(@RequestParam int banned,@RequestParam int id){
        try {
            int delete;
            if(banned == 1){
                delete = userService.updateUserBan(id,Config.FREE_CODE);
            }else{
                delete = userService.updateUserBan(id,Config.BANNED_CODE);
            }
            if(delete > 0){
                return new Response(Config.SUCCESS_CODE,"success",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.ERROR_CODE,"fail",null);
    }


    public Response onError(int id) {
        return new Response(Config.ERROR_CODE,"other micro server die",null);
    }
}

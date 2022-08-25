package com.ning.news.controller;
import com.alibaba.fastjson.JSON;
import com.ning.news.config.Config;
import com.ning.news.entity.Category;
import com.ning.news.entity.User;
import com.ning.news.service.CategoryService;
import com.ning.news.service.UserService;
import com.ning.news.util.Layui;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    UserService userService;

    @Resource
    CategoryService categoryService;

    //所有新闻展示
    @RequestMapping({"/","/index"})
    public String index(Model model){
        return "admin/adminIndex";
    }

    @RequestMapping(value = "/getMemberInfo")
    @ResponseBody
    public Object getMemberInfo() throws Exception {
        List<User> allUser = userService.getUserListByPermission(Config.PRE_TEXT+Config.VIP_USER);
        Layui l = Layui.data(allUser.size(), allUser,"Success");
        return JSON.toJSON(l);
    }

    @RequestMapping(value = "/getAdminInfo")
    @ResponseBody
    public Object getAdminInfo() throws Exception {
        List<User> allUser = userService.getUserListByPermission(Config.PRE_TEXT+Config.ADMIN);
        Layui l = Layui.data(allUser.size(), allUser,"Success");
        return JSON.toJSON(l);
    }

    @RequestMapping(value = "/getEditorInfo")
    @ResponseBody
    public Object getEditorInfo() throws Exception {
        List<User> allUser = userService.getUserListByPermission(Config.PRE_TEXT+Config.PRIMARY_EDITOR);
        List<User> allReviewer = userService.getUserListByPermission(Config.PRE_TEXT+Config.SENIOR_EDITOR);
        allUser.addAll(allReviewer);
        Layui l = Layui.data(allUser.size(), allUser,"Success");
        return JSON.toJSON(l);
    }

    @RequestMapping(value = "/getCategoryInfo")
    @ResponseBody
    public Object getCategoryInfo() throws Exception {
        List<Category> categoryList = categoryService.getAllCategory();
        Layui l = Layui.data(categoryList.size(), categoryList,"Success");
        return JSON.toJSON(l);
    }

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "admin/userInfo";
    }

    @RequestMapping("/adminInfo")
    public String adminInfo() {
        return "admin/adminInfo";
    }

    @RequestMapping("/editorInfo")
    public String editorInfo() {
        return "admin/editorInfo";
    }

    @RequestMapping("/categoryInfo")
    public String categoryInfo() {
        return "admin/categoryInfo";
    }

    @RequestMapping("/delete/user")
    @ResponseBody
    public Object deleteUser(@RequestParam int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user.getId() == id){
            return "same";
        }
        try {
            int delete = userService.deleteUserById(id);
            if(delete > 0){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping("/delete/category")
    @ResponseBody
    public Object deleteCategory(@RequestParam int id){
        if (id == Config.CATEGORY_ORIGIN){
            return "origin";
        }
        try {
            int delete = categoryService.deleteCategoryById(id);
            if(delete > 0){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

    @RequestMapping("/ban/user")
    @ResponseBody
    public Object banUser(@RequestParam int banned,@RequestParam int id, HttpSession session){
        try {
            int delete;
            if(banned == 1){
                delete = userService.updateUserBan(id,Config.FREE_CODE);
            }else{
                delete = userService.updateUserBan(id,Config.BANNED_CODE);
            }
            if(delete > 0){
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "fail";
    }

}

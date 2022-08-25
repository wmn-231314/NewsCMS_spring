package com.ning.news.controller;
import com.ning.news.config.Config;
import com.ning.news.entity.Category;
import com.ning.news.payload.response.Response;
import com.ning.news.entity.User;
import com.ning.news.service.CategoryService;
import com.ning.news.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    UserService userService;

    @Resource
    CategoryService categoryService;

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

    @GetMapping(value = "/getCategoryInfo")
    @ResponseBody
    public Response getCategoryInfo() throws Exception {
        List<Category> categoryList = categoryService.getAllCategory();
//        Layui l = Layui.data(categoryList.size(), categoryList,"Success");
        return new Response(Config.SUCCESS_CODE,"success",categoryList);
    }

    @PostMapping("/delete/user")
    @ResponseBody
    public Response deleteUser(@RequestParam int id, HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user.getId() == id){
            return new Response(Config.ERROR_CODE,"same",null);
        }
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

    @GetMapping("/delete/category")
    @ResponseBody
    public Response deleteCategory(@RequestParam int id){
        if (id == Config.CATEGORY_ORIGIN){
            return new Response(Config.ERROR_CODE,"origin",null);
        }
        try {
            int delete = categoryService.deleteCategoryById(id);
            if(delete > 0){
                return new Response(Config.SUCCESS_CODE,"success",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.ERROR_CODE,"fail",null);
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

}

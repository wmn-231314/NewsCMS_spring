package com.ning.news.controller;

import com.ning.news.entity.Category;
import com.ning.news.entity.News;
import com.ning.news.entity.User;
import com.ning.news.exception.AccountException;
import com.ning.news.param.UserParam;
import com.ning.news.service.CategoryService;
import com.ning.news.service.CommentService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class IndexController {
    @Resource
    private NewsService newsService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    //所有新闻展示
    @RequestMapping({"/","index"})
    public String index(HttpSession session,Model model){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String account = authentication.getName();
                User user = userService.getUserByAccount(account);
                session.setAttribute("user", user);
            }
            List<Category> categories = categoryService.getAllCategory();
            List<News> news = newsService.getAllNews();
            model.addAttribute("categories",categories);
            model.addAttribute("newsList",news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    //所有新闻展示
    @RequestMapping({"login"})
    public String login(Model model){
        return "user/login";
    }

    //注册界面
    @RequestMapping({"register"})
    public String register(Model model){
        return "user/register";
    }

    //所有新闻展示
    @RequestMapping({"logout"})
    public String logout(Model model){
        return "redirect:/index";
    }

    //登陆验证
    @RequestMapping("verify/login")
    public String login(@RequestParam String account,
                        @RequestParam String password,
                        @RequestParam(required = false) String remember_me,
                        HttpSession session, Model model){
        try {
            User user = userService.login(account,password);
            if (user == null) {
                model.addAttribute("msg","用户名或密码错误");
                return "user/login";
            }else {
                session.setAttribute("user",user);
                return "redirect:/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/login";
    }

    //注册验证
    @RequestMapping(value = "verify/register",produces = "application/json;charset=UTF-8")
    public String register(UserParam userParam,Model model){
        if(!Objects.equals(userParam.getPassword(), userParam.getPasswordConfirm())){
            model.addAttribute("msg","The passwords are inconsistent.");
            return "redirect:/register?error";
        }
        try {
            User newUser = new User(userParam);
            System.out.println(newUser);
            User user = userService.register(newUser);
            if (user == null) {
                model.addAttribute("msg","Invalid username or password.");
                return "redirect:/register?error";
            }else {
                return "redirect:/login?register";
            }
        } catch (AccountException e) {
            model.addAttribute("msg","This account already exists.");
            return "redirect:/register?error";
        } catch (Exception e) {
            model.addAttribute("msg","Unknown error.");
            return "redirect:/register?error";
        }
    }
}

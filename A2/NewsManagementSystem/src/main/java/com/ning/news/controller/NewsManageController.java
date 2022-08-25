package com.ning.news.controller;

import com.ning.news.config.Config;
import com.ning.news.entity.*;
import com.ning.news.param.NewsParam;
import com.ning.news.payload.response.Response;
import com.ning.news.service.CategoryService;
import com.ning.news.service.NewsReviewService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/newsManage")
public class NewsManageController {
    @Resource
    NewsService newsService;

    @Resource
    UserService userService;

    @Resource
    NewsReviewService reviewService;

    @Resource
    CategoryService categoryService;

    //添加新闻
    @GetMapping("/add")
    public Response addPage(){
        Map<String,Object> data = new HashMap<>();
        try {
            List<Category>categoryList = categoryService.getAllCategory();
            List<User> reviewerList = userService.getUserListByPermission(Config.PRE_TEXT+Config.SENIOR_EDITOR);
            data.put("reviewers",reviewerList);
            data.put("categories",categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"add page success",data);
    }

    //添加新闻确认
    @PostMapping({"/addVerify"})
    public Response addNews(NewsParam newsParam,HttpSession session){
        News news = new News(newsParam);
        User provider = (User) session.getAttribute("user");
        news.setProviderId(provider.getId());
        NewsReview review = new NewsReview(newsParam);
        try {
            newsService.addNews(news);
            review.setNewsId(news.getId());
            reviewService.addNewsReview(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"add success",null);
    }

    //编辑新闻确认
    @PostMapping({"/editVerify/{news_id}"})
    public Response editNews(@PathVariable("news_id") int nid, NewsParam newsParam){
        News news = new News(newsParam);
        NewsReview review = new NewsReview(newsParam);
        review.setNewsId(nid);
        news.setStatus(Config.REVIEW_CODE);
        news.setId(nid);
        try {
            newsService.updateNews(news);
            reviewService.addNewsReview(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"edit success",null);
    }

    //所有新闻展示
    @GetMapping("/manage")
    public Response managePage(HttpSession session){
        Map<String,Object> data = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            List<News> newsList = newsService.getNewsByProviderId(user.getId());
            List<Category> categoryList = categoryService.getAllCategory();
            data.put("user",user);
            data.put("newsList",newsList);
            data.put("categories",categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Response(Config.SUCCESS_CODE,"manage page success",data);
    }

    //根据新闻id查询新闻
    @GetMapping("/viewNewsDetailById/{id}")
    public Response ViewNewsById(@PathVariable("id") Integer id){
        Map<String,Object> data = new HashMap<>();
        try{
            News news = newsService.getNewsById(id);
            Category category = categoryService.getCategoryById(news.getCategoryId());
            List<Category> categoryList = categoryService.getAllCategory();
            User provider = userService.getUserById(news.getProviderId());
            NewsReview review = reviewService.getNewsReviewByNews(id);
            data.put("news",news);
            data.put("provider",provider);
            data.put("category",category);
            data.put("categories",categoryList);
            data.put("feedback",review.getReviewerDescription());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"view detail success",data);
    }

    //删除新闻确认
    @PostMapping({"/deleteVerify/{news_id}"})
    public Response deleteNews(@PathVariable("news_id") int nid){
        try {
            newsService.deleteNewsById(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"delete success",null);
    }

    //编辑新闻展示
    @GetMapping(value = "/editNews/{news_id}")
    public Response editPage(@PathVariable("news_id") int nid){
        Map<String ,Object> data = new HashMap<>();
        try {
            List<Category>categoryList = categoryService.getAllCategory();
            News news = newsService.getNewsById(nid);
            List<User> reviewerList = userService.getUserListByPermission(Config.PRE_TEXT+Config.SENIOR_EDITOR);
            data.put("reviewers",reviewerList);
            data.put("categories",categoryList);
            data.put("news",news);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"edit page success",data);
    }

}

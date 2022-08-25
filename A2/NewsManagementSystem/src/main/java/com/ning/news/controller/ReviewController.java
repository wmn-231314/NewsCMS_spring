package com.ning.news.controller;

import com.ning.news.config.Config;
import com.ning.news.entity.*;
import com.ning.news.payload.response.Response;
import com.ning.news.service.CategoryService;
import com.ning.news.service.NewsReviewService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    NewsService newsService;

    @Resource
    UserService userService;

    @Resource
    NewsReviewService reviewService;

    @Resource
    CategoryService categoryService;

    //审核新闻展示
    @GetMapping({"/","/manage"})
    public Response reviewPage(HttpSession session){
        Map<String,Object> data = new HashMap<>();
        try {
            User user = (User) session.getAttribute("user");
            List<News> newsList = newsService.getNewsByReviewerId(user.getId());
            List<Category> categoryList = categoryService.getAllCategory();
            data.put("user",user);
            data.put("newsList",newsList);
            data.put("categories",categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"review news",data);
    }

    //通过审核
    @PostMapping({"/reviewApprove/{news_id}"})
    public Response reviewApprove(@PathVariable("news_id") int nid){
        try {
            News news = newsService.getNewsById(nid);
            news.setStatus(Config.PUBLISH_CODE);
            newsService.updateNews(news);
            reviewService.deleteNewsReviewByNews(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"review approve success",null);
    }

    //未通过审核
    @PostMapping({"/reviewRequest/{news_id}"})
    public Response reviewRequest(@PathVariable("news_id") int nid,HttpSession session,@RequestParam String content){
        User user = (User) session.getAttribute("user");
        try {
            int rid = reviewService.getNewsReviewByNews(nid).getId();
            NewsReview newsReview = new NewsReview(nid,user.getId(),Config.DONE_CODE,new Date());
            newsReview.setId(rid);
            newsReview.setReviewerDescription(content);
            News news = newsService.getNewsById(nid);
            news.setStatus(Config.MODIFY_CODE);
            newsService.updateNewsStatus(news);
            reviewService.updateNewsReview(newsReview);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"review request success",null);
    }

    //根据新闻id查询新闻
    @GetMapping("/reviewNewsDetailById/{id}")
    public Response findNewsReviewById(@PathVariable("id") Integer id){
        Map<String,Object> data = new HashMap<>();
        try{
            News news = newsService.getNewsById(id);
            Category category = categoryService.getCategoryById(news.getCategoryId());
            List<Category> categoryList = categoryService.getAllCategory();
            User provider = userService.getUserById(news.getProviderId());
            NewsReview review = reviewService.getNewsReviewByNews(id);
            data.put("news",news);
            data.put("provider",provider);
            data.put("description",review.getCommitterDescription());
            data.put("category",category);
            data.put("categories",categoryList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"news review success",data);
    }
}

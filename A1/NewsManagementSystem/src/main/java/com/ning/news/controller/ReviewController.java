package com.ning.news.controller;

import com.ning.news.config.Config;
import com.ning.news.entity.Category;
import com.ning.news.entity.News;
import com.ning.news.entity.NewsReview;
import com.ning.news.entity.User;
import com.ning.news.param.NewsParam;
import com.ning.news.service.CategoryService;
import com.ning.news.service.NewsReviewService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
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
    @RequestMapping({"/","/manage"})
    public String reviewPage(HttpSession session,Model model){
        try {
            User user = (User) session.getAttribute("user");
            List<News> newsList = newsService.getNewsByReviewerId(user.getId());
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("user",user);
            model.addAttribute("newsList",newsList);
            model.addAttribute("categories",categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "news/reviewNews";
    }

    //通过审核
    @RequestMapping({"/reviewApprove/{news_id}"})
    public String reviewApprove(@PathVariable("news_id") int nid){
        try {
            News news = newsService.getNewsById(nid);
            news.setStatus(Config.PUBLISH_CODE);
            newsService.updateNews(news);
            reviewService.deleteNewsReviewByNews(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/review/manage";
    }

    //未通过审核
    @RequestMapping({"/reviewRequest/{news_id}"})
    public String reviewRequest(@PathVariable("news_id") int nid,HttpSession session, String content){
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
        return "redirect:/review/manage";
    }

    //根据新闻id查询新闻
    @RequestMapping("/reviewNewsDetailById/{id}")
    public String findNewsReviewById(@PathVariable("id") Integer id, Model model){
        try{
            News news = newsService.getNewsById(id);
            Category category = categoryService.getCategoryById(news.getCategoryId());
            List<Category> categoryList = categoryService.getAllCategory();
            User provider = userService.getUserById(news.getProviderId());
            NewsReview review = reviewService.getNewsReviewByNews(id);
            model.addAttribute("news",news);
            model.addAttribute("provider",provider);
            model.addAttribute("description",review.getCommitterDescription());
            model.addAttribute("category",category);
            model.addAttribute("categories",categoryList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "news/reviewNewsDetail";
    }
}

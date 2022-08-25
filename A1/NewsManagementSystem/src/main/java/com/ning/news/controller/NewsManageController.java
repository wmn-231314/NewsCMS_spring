package com.ning.news.controller;

import com.ning.news.config.Config;
import com.ning.news.entity.*;
import com.ning.news.param.NewsParam;
import com.ning.news.service.CategoryService;
import com.ning.news.service.NewsReviewService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
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
    @RequestMapping("/add")
    public String addPage(Model model){
        try {
            List<Category>categoryList = categoryService.getAllCategory();
            List<User> reviewerList = userService.getUserListByPermission(Config.PRE_TEXT+Config.SENIOR_EDITOR);
            model.addAttribute("reviewers",reviewerList);
            model.addAttribute("categories",categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "news/addNews";
    }

    //添加新闻确认
    @RequestMapping({"/addVerify"})
    public String addNews(NewsParam newsParam,HttpSession session){
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
        return "redirect:/newsManage/manage";
    }

    //编辑新闻确认
    @RequestMapping({"/editVerify/{news_id}"})
    public String editNews(@PathVariable("news_id") int nid, NewsParam newsParam){
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
        return "redirect:/newsManage/manage";
    }

    //所有新闻展示
    @RequestMapping("/manage")
    public String managePage(HttpSession session,Model model){
        try {
            User user = (User) session.getAttribute("user");
            List<News> newsList = newsService.getNewsByProviderId(user.getId());
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("user",user);
            model.addAttribute("newsList",newsList);
            model.addAttribute("categories",categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "news/manageNews";
    }

    //根据新闻id查询新闻
    @RequestMapping("/viewNewsDetailById/{id}")
    public String ViewNewsById(@PathVariable("id") Integer id, Model model){
        try{
            News news = newsService.getNewsById(id);
            Category category = categoryService.getCategoryById(news.getCategoryId());
            List<Category> categoryList = categoryService.getAllCategory();
            User provider = userService.getUserById(news.getProviderId());
            NewsReview review = reviewService.getNewsReviewByNews(id);
            model.addAttribute("news",news);
            model.addAttribute("provider",provider);
            model.addAttribute("category",category);
            model.addAttribute("categories",categoryList);
            model.addAttribute("feedback",review.getReviewerDescription());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "news/editNewsDetail";
    }

    //删除新闻确认
    @RequestMapping({"/deleteVerify/{news_id}"})
    public String deleteNews(@PathVariable("news_id") int nid){
        try {
            newsService.deleteNewsById(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/newsManage/manage";
    }

    //编辑新闻展示
    @RequestMapping(value = "/editNews/{news_id}")
    public String editPage(@PathVariable("news_id") int nid, Model model){
        try {
            List<Category>categoryList = categoryService.getAllCategory();
            News news = newsService.getNewsById(nid);
            List<User> reviewerList = userService.getUserListByPermission(Config.PRE_TEXT+Config.SENIOR_EDITOR);
            model.addAttribute("reviewers",reviewerList);
            model.addAttribute("categories",categoryList);
            model.addAttribute("news",news);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "news/editNews";
    }

}

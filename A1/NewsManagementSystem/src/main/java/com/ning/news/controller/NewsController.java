package com.ning.news.controller;


import com.ning.news.config.Config;
import com.ning.news.entity.Category;
import com.ning.news.entity.Comment;
import com.ning.news.entity.News;
import com.ning.news.entity.User;
import com.ning.news.param.UserParam;
import com.ning.news.service.CategoryService;
import com.ning.news.service.CommentService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @Resource
    private CategoryService categoryService;

    @Resource
    private UserService userService;

    @Resource
    private CommentService commentService;

    //根据新闻id查询新闻
    @RequestMapping("/getNewsDetailById/{id}")
    public String findNewsById(@PathVariable("id") Integer id,HttpSession session, Model model){
        try{
            News news = newsService.getNewsById(id);
            Category category = categoryService.getCategoryById(news.getCategoryId());
            List<Category> categoryList = categoryService.getAllCategory();
            User provider = userService.getUserById(news.getProviderId());
            List<Comment> commentList = commentService.getCommentByNews(id);
            User user = (User) session.getAttribute("user");
            if (user != null){
                Comment yourComment = commentService.getCommentByUserNews(user.getId(),id);
                if(yourComment != null){
                    model.addAttribute("yourComment",yourComment.getContent());
                }
            }
            model.addAttribute("news",news);
            model.addAttribute("category",category);
            model.addAttribute("provider",provider);
            model.addAttribute("categories",categoryList);
            model.addAttribute("comments",commentList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "news/newsDetail";
    }

    //根据新闻标题查询新闻
    @RequestMapping("/searchNews")
    public String findNewsByTitle(String content, Model model){
        try{
            List<Category> categories = categoryService.getAllCategory();
            News searchNews = new News();
            if(content.startsWith("#") && content.endsWith("#")){
                Category category = categoryService.getCategoryByName(content.substring(1,content.length()-1));
                if(category != null){
                    searchNews.setCategoryId(category.getId());
                }
            }else{
                searchNews.setTitle(content);
            }
            List<News> newsList = newsService.getSearchedNews(searchNews);
            model.addAttribute("content",content);
            model.addAttribute("categories",categories);
            model.addAttribute("newsList",newsList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "news/searchNews";
    }

    //进入类别界面
    @RequestMapping("/findNewsByCategory/{category_id}")
    public String findNewsByCategory(@PathVariable("category_id") Integer category_id, Model model){
        try {
            Category category = categoryService.getCategoryById(category_id);
            List<News> newsList = newsService.getNewsByCategory(category_id);
            for(News news:newsList){
                System.out.println(news);
            }
            List<Category> categoryList = categoryService.getAllCategory();
            model.addAttribute("categories",categoryList);
            model.addAttribute("curCategory",category);
            model.addAttribute("newsList",newsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "news/category";
    }
}

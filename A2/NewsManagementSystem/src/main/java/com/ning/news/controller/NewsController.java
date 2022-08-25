package com.ning.news.controller;


import com.ning.news.config.Config;
import com.ning.news.entity.*;
import com.ning.news.payload.response.Response;
import com.ning.news.service.CategoryService;
import com.ning.news.service.CommentService;
import com.ning.news.service.NewsService;
import com.ning.news.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
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
    @GetMapping("/getNewsDetailById/{id}")
    public Response findNewsById(@PathVariable("id") Integer id, HttpSession session){
        Map<String,Object> data = new HashMap<>();
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
                    data.put("yourComment",yourComment.getContent());
                }
            }
            data.put("news",news);
            data.put("category",category);
            data.put("provider",provider);
            data.put("categories",categoryList);
            data.put("comments",commentList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"news detail success",data);
    }

    @GetMapping(value = {"/getCategory"}, name = "获取分类")
    public Response getAllCategory(){
        Map<String,Object> data = new HashMap<>();
        try{
            List<Category> categories = categoryService.getAllCategory();
            data.put("categories",categories);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"get category success",data);
    }

    //根据新闻标题查询新闻
    @GetMapping("/searchNews")
    public Response findNewsByTitle(String content){
        Map<String,Object> data = new HashMap<>();
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
            data.put("content",content);
            data.put("categories",categories);
            data.put("newsList",newsList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"Search success",data);
    }

    //进入类别界面
    @GetMapping("/findNewsByCategory/{category_id}")
    public Response findNewsByCategory(@PathVariable("category_id") Integer category_id){
        Map<String,Object> data = new HashMap<>();
        try {
            Category category = categoryService.getCategoryById(category_id);
            List<News> newsList = newsService.getNewsByCategory(category_id);
            for(News news:newsList){
                System.out.println(news);
            }
            List<Category> categoryList = categoryService.getAllCategory();
            data.put("categories",categoryList);
            data.put("curCategory",category);
            data.put("newsList",newsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"Category News success",data);
    }
}

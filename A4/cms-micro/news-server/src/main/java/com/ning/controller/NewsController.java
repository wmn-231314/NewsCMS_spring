package com.ning.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ning.entity.News;
import com.ning.payload.response.Response;
import com.ning.service.NewsService;
import com.ning.utils.Config;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    @Resource
    private KafkaTemplate template;

    //根据新闻id查询新闻
    @GetMapping("/getNewsById/{id}")
    public Response findNewsById(@PathVariable("id") Integer id){
        Map<String,Object> data = new HashMap<>();
        try{
            News news = newsService.getNewsById(id);
            data.put("news",news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"news detail success",data);
    }

    //根据新闻id查询新闻
    @GetMapping("/getAllNews")
    public Response getAllNews(){
        Map<String,Object> data = new HashMap<>();
        try{
            List<News> news = newsService.getAllNews();
            template.send("news", "kafka getting news");
            data.put("newsList",news);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"all news success",data);
    }

    @KafkaListener(id = "newgetAll", topics = "news", groupId = "news-test")
    public void listener(String input) {
        System.out.println("收到消息：" + input);
    }

    //根据新闻标题查询新闻
    @GetMapping("/searchNews")
    public Response findNewsByTitle(String content){
        Map<String,Object> data = new HashMap<>();
        try{
            News searchNews = new News();
            searchNews.setTitle(content);
            List<News> newsList = newsService.getSearchedNews(searchNews);
            data.put("content",content);
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
            List<News> newsList = newsService.getNewsByCategory(category_id);
            for(News news:newsList){
                System.out.println(news);
            }
            data.put("newsList",newsList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"Category News success",data);
    }

    //进入类别界面
    @GetMapping("/getByCategory/{category_id}")
    public List<News> getNewsByCategory(@PathVariable("category_id") Integer category_id){
        try {
            List<News> newsList = newsService.getNewsByCategory(category_id);
            for(News news:newsList){
                System.out.println(news);
            }
            return newsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("deleteNewsByProviderId/{id}")
    public Response deleteNewsByProviderId(@PathVariable int id){
        int delete = newsService.deleteNewsByProviderId(id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    @PostMapping("deleteNewsById/{id}")
    @HystrixCommand(fallbackMethod = "onError")
    public Response deleteNewsById(@PathVariable int id){
        int delete = newsService.deleteNewsById(id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    @PostMapping("updateNews")
    @HystrixCommand(fallbackMethod = "onNError")
    public Response updateNews(@RequestParam News news){
        int update = newsService.updateNews(news);
        if (update > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    @PostMapping("updateStatus")
    public Response updateNewsStatus(@RequestParam News news){
        int update = newsService.updateNewsStatus(news);
        if (update > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    public Response onError(int id) {
        return new Response(Config.ERROR_CODE,"other micro server die",null);
    }
    public Response onNError(News id) {
        return new Response(Config.ERROR_CODE,"other micro server die",null);
    }
}

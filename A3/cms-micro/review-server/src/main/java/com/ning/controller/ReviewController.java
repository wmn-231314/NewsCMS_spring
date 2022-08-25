package com.ning.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ning.entity.News;
import com.ning.entity.NewsReview;
import com.ning.entity.User;
import com.ning.payload.response.Response;
import com.ning.service.NewsReviewService;
import com.ning.utils.Config;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @Resource
    NewsReviewService reviewService;

    //通过审核
    @PostMapping({"/reviewApprove/{news_id}"})
    @HystrixCommand(fallbackMethod = "onError")
    public Response reviewApprove(@PathVariable("news_id") int nid){
        try {
            News news = new News();
            news.setId(nid);
            news.setStatus(Config.PUBLISH_CODE);
            reviewService.updateNews(news);
            reviewService.deleteNewsReviewByNews(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"review approve success",null);
    }

    //未通过审核
    @PostMapping({"/reviewRequest/{news_id}"})
    @HystrixCommand(fallbackMethod = "onUError")
    public Response reviewRequest(@PathVariable("news_id") int nid,@RequestParam String content,@RequestParam User user){
        try {
            int rid = reviewService.getNewsReviewByNews(nid).getId();
            NewsReview newsReview = new NewsReview(nid,user.getId(),Config.DONE_CODE,new Date());
            newsReview.setId(rid);
            newsReview.setReviewerDescription(content);
            News news = new News();
            news.setId(nid);
            news.setStatus(Config.MODIFY_CODE);
            reviewService.updateNewsStatus(news);
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
            NewsReview review = reviewService.getNewsReviewByNews(id);
            data.put("review",review);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(Config.SUCCESS_CODE,"news review success",data);
    }

    //通过审核
    @PostMapping({"/deleteReview/{id}"})
    public Response deleteNewsReviewById(@PathVariable int id){
        int delete = reviewService.deleteNewsReviewByNews(id);
        if (delete > 0){
            return new Response(Config.SUCCESS_CODE,"Delete success",null);
        }
        return new Response(Config.ERROR_CODE,"Delete fail",null);
    }

    public Response onError(int id) {
        return new Response(Config.ERROR_CODE,"other micro server die",null);
    }
    public Response onUError(int id,String content,User user) {
        return new Response(Config.ERROR_CODE,"other micro server die",null);
    }
}

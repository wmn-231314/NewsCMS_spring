package com.ning.news.service.impl;

import com.ning.news.BaseTest;
import com.ning.news.config.Config;
import com.ning.news.entity.News;
import com.ning.news.service.NewsService;
import org.apache.catalina.LifecycleState;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class NewsServiceImplTest extends BaseTest {
    @Autowired
    NewsService newsService;

    @Test
    public void getAllNews() {
        try {
            List<News> newsList = newsService.getAllNews();
            System.out.println("get all");
            for(News news:newsList){
                System.out.println(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNewsByProviderId() {
        int pid = 2;
        try {
            List<News> newsList = newsService.getNewsByProviderId(pid);
            System.out.println("get by pid");
            for(News news:newsList){
                System.out.println(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNewsById() {
        int id = 41;
        try {
            News news = newsService.getNewsById(id);
            System.out.println("get by id"+news);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSearchedNews() {
        News news = new News();
        news.setTitle("New");
        try {
            List<News> newsList = newsService.getSearchedNews(news);
            System.out.println("get searched");
            for(News news1:newsList){
                System.out.println(news1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNewsByStatus() {
        String status = Config.MODIFY_CODE;
        try {
            List<News> newsList = newsService.getNewsByStatus(status);
            System.out.println("get by status");
            for(News news:newsList){
                System.out.println(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNewsByCategory() {
        int categoryId = 0;
        try {
            List<News> newsList = newsService.getNewsByCategory(categoryId);
            System.out.println("get by category");
            for(News news:newsList){
                System.out.println(news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addNews() {
        News news = new News(0,"测试增加");
        news.setContent("正文正文正文");
        try {
            int res = newsService.addNews(news);
            System.out.println("success add "+res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateNews() {
        News news = null;
        try {
            news = newsService.getNewsById(42);
            news.setContent(news.getContent()+"更新更新");
            int res = newsService.updateNews(news);
            System.out.println("update success "+res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteNewsById() {
        int id = 41;
        try {
            int res = newsService.deleteNewsById(id);
            System.out.println("delete id "+res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteNewsByList() {
        try {
            List<News> newsList = newsService.getAllNews();
            int len = newsList.size();
            for(int i = 0; i < len-2; i++){
                newsList.remove(0);
            }
            int res = newsService.deleteNewsByList(newsList);
            System.out.println("delete list"+res);
            getAllNews();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteNewsByStatus() {
        String status = Config.CANCEL_CODE;
        try {
            int res = newsService.deleteNewsByStatus(status);
            System.out.println("delete status "+res);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
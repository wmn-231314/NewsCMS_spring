package com.ning.news.mapper;

import com.ning.news.BaseTest;
import com.ning.news.entity.News;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class NewsMapperTest extends BaseTest {
    @Autowired
    NewsMapper newsMapper;

    @Test
    public void getAllNews() {
        List<News> newsList = newsMapper.getAllNews();
        System.out.println("all news");
        for(News news:newsList){
            System.out.println(news);
        }
    }

    @Test
    public void getNewsByProviderId() {
        int pid = 2;
        List<News> newsList = newsMapper.getNewsByProviderId(pid);
        System.out.println("get by pid");
        for(News news:newsList){
            System.out.println(news);
        }
    }

    @Test
    public void getNewsById() {
        int nid = 41;
        News news = newsMapper.getNewsById(nid);
        System.out.println("get by id"+news);
    }

    @Test
    public void getNewsByCategory() {
        int categoryId = 0;
        List<News> newsList = newsMapper.getNewsByCategory(categoryId);
        System.out.println("category");
        for(News news1:newsList){
            System.out.println(news1);
        }
    }

    @Test
    public void getSearchedNews() {
        News news = new News();
        news.setTitle("New");
        System.out.println(news);
        List<News> newsList = newsMapper.getSearchedNews(news);
        System.out.println("searched news");
        for(News news1:newsList){
            System.out.println(news1);
        }
    }

    @Test
    public void getNewsByStatus() {
        String status = "Published";
        List<News> newsList = newsMapper.getNewsByStatus(status);
        System.out.println("get by status");
        for(News news:newsList){
            System.out.println(news);
        }
    }

    @Test
    public void addNews() {
        News news = new News(1,"测试添加新闻");
        news.setContent("正文正文正文正文正文正文正文正文正文正文");
        int res = newsMapper.addNews(news);
        System.out.println("add"+res);
        System.out.println(news);
    }

    @Test
    public void updateNews() {
        News news = newsMapper.getNewsById(41);
        news.setContent(news.getContent()+"update");
        int res = newsMapper.updateNews(news);
        news = newsMapper.getNewsById(41);
        System.out.println("update"+res);
        System.out.println(news);
    }

    @Test
    public void deleteNewsById() {
        int id = 103;
        getAllNews();
        int res = newsMapper.deleteNewsById(id);
        System.out.println("delete id"+res);
        System.out.println(newsMapper.getNewsById(id));
    }

    @Test
    public void deleteNewsByList() {
        List<News> newsList = newsMapper.getAllNews();
        int len = newsList.size();
        for(int i = 0; i < len-2; i++){
            newsList.remove(0);
        }
        int res = newsMapper.deleteNewsByList(newsList);
        System.out.println("delete list"+res);
        getAllNews();
    }

    @Test
    public void deleteNewsByStatus() {
        String status = "Canceled";
        int res = newsMapper.deleteNewsByStatus(status);
        System.out.println("delete st"+res);
        getAllNews();
    }

}
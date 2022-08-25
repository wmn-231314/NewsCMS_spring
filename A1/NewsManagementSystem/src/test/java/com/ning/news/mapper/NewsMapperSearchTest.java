package com.ning.news.mapper;

import com.ning.news.BaseTest;
import com.ning.news.entity.News;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class NewsMapperSearchTest extends BaseTest {
    @Autowired
    NewsMapper newsMapper;

    @Test
    public void getSearchedNews() {
        News news = new News();
        news.setCategoryId(0);
        System.out.println(news);
        List<News> newsList = newsMapper.getSearchedNews(news);
        System.out.println("searched news");
        for(News news1:newsList){
            System.out.println(news1);
        }
    }
}
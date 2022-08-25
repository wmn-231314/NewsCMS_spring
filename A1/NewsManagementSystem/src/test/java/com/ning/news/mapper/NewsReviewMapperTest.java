package com.ning.news.mapper;

import com.ning.news.BaseTest;
import com.ning.news.config.Config;
import com.ning.news.entity.NewsReview;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class NewsReviewMapperTest extends BaseTest {
    @Autowired
    NewsReviewMapper newsReviewMapper;

    @Test
    public void addNewsReview() {
        NewsReview newsReview = new NewsReview(3,1,"askdjfha", Config.WAIT_CODE,new Date());
        int res = newsReviewMapper.addNewsReview(newsReview);
        System.out.println("add"+res);
        System.out.println(newsReview);
    }

    @Test
    public void getReviewByNews() {
        int nid = 2;
        NewsReview newsReview = newsReviewMapper.getReviewByNews(nid);
        System.out.println(newsReview);
    }

    @Test
    public void updateNewsReview() {
        int nid = 2;
        NewsReview newsReview = newsReviewMapper.getReviewByNews(nid);
        newsReview.setReviewerId(1);
        newsReview.setStatus(Config.DONE_CODE);
        newsReview.setReviewerDescription("测试中文");
        newsReview.setReviewTime(new Date());
        int res = newsReviewMapper.updateNewsReview(newsReview);
        System.out.println("update"+res);
        newsReview = newsReviewMapper.getReviewByNews(nid);
        System.out.println(newsReview);
    }

    @Test
    public void getAllReview() {
        List<NewsReview> newsReviewList = newsReviewMapper.getAllReview();
        for(NewsReview newsReview:newsReviewList){
            System.out.println(newsReview);
        }
    }

    @Test
    public void getReviewByReviewer() {
        int rid = 1;
        List<NewsReview> newsReviewList = newsReviewMapper.getReviewByReviewer(rid);
        for(NewsReview newsReview:newsReviewList){
            System.out.println(newsReview);
        }
    }

    @Test
    public void deleteNewsReview() {
        int nid = 2;
        NewsReview newsReview = newsReviewMapper.getReviewByNews(nid);
        int res = newsReviewMapper.deleteNewsReview(newsReview);
        System.out.println("delete"+res);
    }

    @Test
    public void deleteNewsReviewByNews() {
        int nid = 3;
        int res = newsReviewMapper.deleteNewsReviewByNews(nid);
        System.out.println("delete"+res);
    }

    @Test
    public void deleteNewsReviewByReviewer() {
        int rid = 1;
        int res = newsReviewMapper.deleteNewsReviewByReviewer(rid);
        System.out.println("delete"+res);
    }


}
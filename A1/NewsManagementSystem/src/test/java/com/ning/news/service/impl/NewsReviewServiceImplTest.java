package com.ning.news.service.impl;

import com.ning.news.BaseTest;
import com.ning.news.config.Config;
import com.ning.news.entity.NewsReview;
import com.ning.news.service.NewsReviewService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class NewsReviewServiceImplTest extends BaseTest {
    @Autowired
    NewsReviewService newsReviewService;

    @Test
    public void getAllNewsReview() {
        try {
            List<NewsReview> newsReviewList = newsReviewService.getAllNewsReview();
            System.out.println(newsReviewList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNewReviewByReviewer() {
        int rid = 1;
        List<NewsReview> newsReviewList = null;
        try {
            newsReviewList = newsReviewService.getNewReviewByReviewer(rid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(NewsReview newsReview:newsReviewList){
            System.out.println(newsReview);
        }
    }

    @Test
    public void getNewsReviewByNews() {
        int nid = 2;
        NewsReview newsReview = null;
        try {
            newsReview = newsReviewService.getNewsReviewByNews(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(newsReview);
    }

    @Test
    public void updateNewsReview() {
        int nid = 2;
        NewsReview newsReview = null;
        try {
            newsReview = newsReviewService.getNewsReviewByNews(nid);
            newsReview.setReviewerId(1);
            newsReview.setStatus(Config.DONE_CODE);
            newsReview.setReviewerDescription("测试中文");
            newsReview.setReviewTime(new Date());
            int res = newsReviewService.updateNewsReview(newsReview);
            System.out.println("update"+res);
            newsReview = newsReviewService.getNewsReviewByNews(nid);
            System.out.println(newsReview);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void addNewsReview() {
        NewsReview newsReview = new NewsReview(2,1,"askdjfha", Config.WAIT_CODE,new Date());
        int res = 0;
        try {
            res = newsReviewService.addNewsReview(newsReview);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("add"+res);
        System.out.println(newsReview);
    }

    @Test
    public void deleteNewsReview() {
        int nid = 2;
        NewsReview newsReview = null;
        try {
            newsReview = newsReviewService.getNewsReviewByNews(nid);
            int res = newsReviewService.deleteNewsReview(newsReview);
            System.out.println("delete"+res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteNewsReviewByNews() {
        int nid = 3;
        int res = 0;
        try {
            res = newsReviewService.deleteNewsReviewByNews(nid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("delete"+res);
    }

    @Test
    public void deleteNewsReviewByReviewer() {
        int rid = 1;
        int res = 0;
        try {
            res = newsReviewService.deleteNewsReviewByReviewer(rid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("delete"+res);
    }

}
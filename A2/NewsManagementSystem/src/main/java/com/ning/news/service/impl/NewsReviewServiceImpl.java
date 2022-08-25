package com.ning.news.service.impl;

import com.ning.news.config.Config;
import com.ning.news.entity.NewsReview;
import com.ning.news.mapper.NewsReviewMapper;
import com.ning.news.service.NewsReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsReviewServiceImpl implements NewsReviewService {
    @Autowired
    NewsReviewMapper newsReviewMapper;

    @Override
    public List<NewsReview> getAllNewsReview() throws Exception {
        return newsReviewMapper.getAllReview();
    }

    @Override
    public List<NewsReview> getNewReviewByReviewer(int rid) throws Exception {
        return newsReviewMapper.getReviewByReviewer(rid);
    }

    @Override
    public NewsReview getNewsReviewByNews(int nid) throws Exception {
        return newsReviewMapper.getReviewByNews(nid);
    }

    @Override
    public int addNewsReview(NewsReview review) throws Exception {
        review.setCommitTime(new Date());
        review.setStatus(Config.WAIT_CODE);
        return newsReviewMapper.addNewsReview(review);
    }

    @Override
    public int deleteNewsReview(NewsReview newsReview) throws Exception {
        return newsReviewMapper.deleteNewsReview(newsReview);
    }

    @Override
    public int deleteNewsReviewByNews(int nid) throws Exception {
        return newsReviewMapper.deleteNewsReviewByNews(nid);
    }

    @Override
    public int deleteNewsReviewByReviewer(int rid) throws Exception {
        return newsReviewMapper.deleteNewsReviewByReviewer(rid);
    }

    @Override
    public int updateNewsReview(NewsReview newsReview) throws Exception {
        return newsReviewMapper.updateNewsReview(newsReview);
    }
}

package com.ning.service.impl;

import com.ning.entity.News;
import com.ning.entity.NewsReview;
import com.ning.mapper.NewsReviewMapper;
import com.ning.payload.response.Response;
import com.ning.service.NewsReviewService;
import com.ning.service.client.NewsClient;
import com.ning.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsReviewServiceImpl implements NewsReviewService {
    @Resource
    NewsReviewMapper newsReviewMapper;

    @Resource
    NewsClient newsClient;

    @Override
    public List<NewsReview> getAllNewsReview(){
        return newsReviewMapper.getAllReview();
    }

    @Override
    public List<NewsReview> getNewReviewByReviewer(int rid){
        return newsReviewMapper.getReviewByReviewer(rid);
    }

    @Override
    public NewsReview getNewsReviewByNews(int nid) {
        return newsReviewMapper.getReviewByNews(nid);
    }

    @Override
    public int addNewsReview(NewsReview review){
        review.setCommitTime(new Date());
        review.setStatus(Config.WAIT_CODE);
        return newsReviewMapper.addNewsReview(review);
    }

    @Override
    public int deleteNewsReview(NewsReview newsReview){
        return newsReviewMapper.deleteNewsReview(newsReview);
    }

    @Override
    public int deleteNewsReviewByNews(int nid){
        return newsReviewMapper.deleteNewsReviewByNews(nid);
    }

    @Override
    public int deleteNewsReviewByReviewer(int rid){
        return newsReviewMapper.deleteNewsReviewByReviewer(rid);
    }

    @Override
    public int updateNewsReview(NewsReview newsReview){
        return newsReviewMapper.updateNewsReview(newsReview);
    }

    @Override
    public int updateNews(News news) {
        Response response = newsClient.updateNews(news);
        if(response.getCode() == 200){
            return 1;
        }
        return 0;
    }

    @Override
    public int updateNewsStatus(News news) {
        Response response = newsClient.updateNewsStatus(news);
        if(response.getCode() == 200){
            return 1;
        }
        return 0;
    }

}

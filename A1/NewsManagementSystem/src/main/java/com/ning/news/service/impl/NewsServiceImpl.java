package com.ning.news.service.impl;

import com.ning.news.config.Config;
import com.ning.news.entity.News;
import com.ning.news.entity.NewsReview;
import com.ning.news.mapper.CommentMapper;
import com.ning.news.mapper.NewsMapper;
import com.ning.news.mapper.NewsReviewMapper;
import com.ning.news.service.NewsReviewService;
import com.ning.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Autowired
    NewsReviewMapper newsReviewMapper;

    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<News> getAllNews() throws Exception {
        return newsMapper.getAllNews();
    }

    @Override
    public List<News> getNewsByProviderId(int pid) throws Exception {
        return newsMapper.getNewsByProviderId(pid);
    }

    @Override
    public List<News> getNewsByReviewerId(int rid) throws Exception {
        return newsMapper.getNewsByReviewerId(rid);
    }


    @Override
    public News getNewsById(int id) throws Exception {
        return newsMapper.getNewsById(id);
    }

    @Override
    public List<News> getNewsByCategory(int categoryId) throws Exception {
        return newsMapper.getNewsByCategory(categoryId);
    }

    @Override
    public List<News> getSearchedNews(News news) throws Exception {
        return newsMapper.getSearchedNews(news);
    }

    @Override
    public List<News> getNewsByStatus(String status) throws Exception {
        return newsMapper.getNewsByStatus(status);
    }

    @Override
    public int addNews(News news) throws Exception {
        news.setStatus(Config.REVIEW_CODE);
        news.setPostTime(new Date());
        return newsMapper.addNews(news);
    }

    @Override
    public int updateNews(News news) throws Exception {
        news.setPostTime(new Date());
        newsReviewMapper.deleteNewsReviewByNews(news.getId());
        commentMapper.deleteCommentByNews(news.getId());
        return newsMapper.updateNews(news);
    }

    @Override
    public int updateNewsStatus(News news) throws Exception {
        return newsMapper.updateNews(news);
    }

    @Override
    public int deleteNewsById(int id) throws Exception {
        newsReviewMapper.deleteNewsReviewByNews(id);
        commentMapper.getCommentByNews(id);
        return newsMapper.deleteNewsById(id);
    }

    @Override
    public int deleteNewsByList(List<News> news) throws Exception {
        return newsMapper.deleteNewsByList(news);
    }

    @Override
    public int deleteNewsByStatus(String status) throws Exception {
        return newsMapper.deleteNewsByStatus(status);
    }
}

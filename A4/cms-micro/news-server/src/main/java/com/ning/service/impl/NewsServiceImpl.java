package com.ning.service.impl;

import com.ning.entity.News;
import com.ning.mapper.NewsMapper;
import com.ning.service.NewsService;
import com.ning.service.client.CommentClient;
import com.ning.service.client.ReviewClient;
import com.ning.utils.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    NewsMapper newsMapper;

    @Resource
    CommentClient commentClient;

    @Resource
    ReviewClient reviewClient;

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
    public int updateNews(News news){
        news.setPostTime(new Date());
        reviewClient.deleteNewsReviewById(news.getId());
        commentClient.deleteByNews(news.getId());
        return newsMapper.updateNews(news);
    }

    @Override
    public int updateNewsStatus(News news){
        return newsMapper.updateNews(news);
    }

    @Override
    public int deleteNewsById(int id){
        reviewClient.deleteNewsReviewById(id);
        commentClient.deleteByNews(id);
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

    public int deleteNewsByProviderId(int pid){
        return newsMapper.deleteNewsByProviderId(pid);
    }
}

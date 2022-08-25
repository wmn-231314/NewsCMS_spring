package com.ning.news.mapper;

import com.ning.news.entity.News;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface NewsMapper {
    List<News> getAllNews();
    List<News> getNewsByProviderId(int pid);
    List<News> getNewsByReviewerId(int rid);
    News getNewsById(int id);
    List<News> getSearchedNews(News news);
    List<News> getNewsByStatus(String status);
    List<News> getNewsByCategory(int categoryId);

    int addNews(News news);

    int updateNews(News news);

    int deleteNewsById(int id);
    int deleteNewsByProviderId(int pid);
    int deleteNewsByReviewerId(int rid);
    int deleteNewsByList(List<News> news);
    int deleteNewsByStatus(String status);

}

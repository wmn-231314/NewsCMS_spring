package com.ning.service;

import com.ning.entity.News;

import java.util.List;

public interface NewsService {
    /**
     * 获取所有新闻
     * @return List
     */
    List<News> getAllNews() throws Exception;

    /**
     * 通过发布者id获取新闻
     * @param pid;
     * @return List
     */
    List<News> getNewsByProviderId(int pid) throws Exception;

    /**
     * 通过审核者id获取新闻
     * @param rid;
     * @return List
     */
    List<News> getNewsByReviewerId(int rid) throws Exception;

    /**
     * 通过id获取新闻
     * @param id;
     * @return News
     */
    News getNewsById(int id) throws Exception;

    /**
     * 通过categoryId获取新闻
     * @param categoryId;
     * @return List
     */
    List<News> getNewsByCategory(int categoryId) throws Exception;

    /**
     * 搜索新闻
     * @param news;
     * @return List
     */
    List<News> getSearchedNews(News news) throws Exception;

    /**
     * 通过状态获取新闻
     * @param status;
     * @return List
     */
    List<News> getNewsByStatus(String status) throws Exception;

    /**
     * 添加新闻
     * @param news;
     * @return int
     */
    int addNews(News news) throws Exception;

    /**
     * 更新新闻信息
     * @param news;
     * @return int
     */
    int updateNews(News news);

    int updateNewsStatus(News news);

    /**
     * 通过id删除新闻
     * @param id;
     * @return int
     */
    int deleteNewsById(int id);

    int deleteNewsByProviderId(int pid);

    /**
     * 通过列表删除新闻
     * @param news;
     * @return int
     */
    int deleteNewsByList(List<News> news) throws Exception;

    /**
     * 通过状态删除新闻
     * @param status
     * @return int
     */
    int deleteNewsByStatus(String status) throws Exception;
}

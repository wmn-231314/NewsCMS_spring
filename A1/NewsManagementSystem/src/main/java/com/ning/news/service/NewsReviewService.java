package com.ning.news.service;

import com.ning.news.entity.NewsReview;

import java.util.List;

public interface NewsReviewService {
    /**
     * 获取所有修改审核
     * @return List
     */
    List<NewsReview> getAllNewsReview() throws Exception;

    /**
     * 通过审核人id获取修改审核
     * @param rid;
     * @return List
     */
    List<NewsReview> getNewReviewByReviewer(int rid) throws Exception;

    /**
     * 通过新闻id获取修改审核
     * @param nid;
     * @return NewsReview
     */
    NewsReview getNewsReviewByNews(int nid) throws Exception;


    /**
     * 添加修改审核
     * @param review;
     * @return int
     */
    int addNewsReview(NewsReview review) throws Exception;


    /**
     * 删除修改审核
     * @param newsReview;
     * @return int
     */
    int deleteNewsReview(NewsReview newsReview) throws Exception;

    /**
     * 通过新闻id删除修改审核
     * @param nid;
     * @return int
     */
    int deleteNewsReviewByNews(int nid) throws Exception;

    /**
     * 通过审核人id删除修改审核
     * @param rid;
     * @return int
     */
    int deleteNewsReviewByReviewer(int rid) throws Exception;


    /**
     * 更新修改审核信息
     * @param newsReview;
     * @return int
     */
    int updateNewsReview(NewsReview newsReview) throws Exception;
}

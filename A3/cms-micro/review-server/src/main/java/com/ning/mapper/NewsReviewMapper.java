package com.ning.mapper;

import com.ning.entity.NewsReview;

import java.util.List;

public interface NewsReviewMapper {
    List<NewsReview> getAllReview();
    List<NewsReview> getReviewByReviewer(int rid);
    NewsReview getReviewByNews(int nid);

    int addNewsReview(NewsReview newsReview);

    int deleteNewsReview(NewsReview newsReview);
    int deleteNewsReviewByNews(int nid);
    int deleteNewsReviewByReviewer(int rid);

    int updateNewsReview(NewsReview newsReview);
}

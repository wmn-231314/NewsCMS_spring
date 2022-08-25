package com.ning.entity;

import com.ning.param.NewsParam;
import com.ning.utils.Config;

import java.util.Date;

public class News{
    private int id;
    private int providerId;
    private int reviewerId;
    private String title;
    private String content;
    private String status;
    private Date postTime;
    private int articleLikeCount;
    private int categoryId;

    public News(NewsParam newsParam){
        if (newsParam.getProviderId() > 0) this.providerId = newsParam.getProviderId();
        if (newsParam.getReviewerId() > 0) this.reviewerId = newsParam.getReviewerId();
        if (newsParam.getTitle() != null) this.title = newsParam.getTitle();
        if (newsParam.getContent() != null) this.content = newsParam.getContent();
        if (newsParam.getStatus() != null) this.status=newsParam.getStatus();
        if (newsParam.getPostTime() != null) this.postTime=newsParam.getPostTime();
        if (newsParam.getArticleLikeCount() > 0) this.articleLikeCount=newsParam.getArticleLikeCount();
        if (newsParam.getCategoryId() > 0) this.categoryId=newsParam.getCategoryId();
    }

    public News(int providerId, String title) {
        this.providerId = providerId;
        this.title = title;
        this.status = Config.REVIEW_CODE;
    }

    public News(int id, int providerId, String title) {
        this.id = id;
        this.providerId = providerId;
        this.title = title;
        this.status = Config.REVIEW_CODE;
    }

    public News(int providerId, int reviewerId, String title, String content) {
        this.providerId = providerId;
        this.reviewerId = reviewerId;
        this.title = title;
        this.content = content;
        this.status = Config.REVIEW_CODE;
    }

    public News() {
        this.id = -1;
        this.providerId = -1;
        this.reviewerId = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public int getArticleLikeCount() {
        return articleLikeCount;
    }

    public void setArticleLikeCount(int articleLikeCount) {
        this.articleLikeCount = articleLikeCount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", providerId=" + providerId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", postTime=" + postTime +
                ", articleLikeCount=" + articleLikeCount +
                ", categoryId='" + categoryId +
                '}';
    }
}

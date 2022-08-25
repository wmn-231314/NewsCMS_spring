package com.ning.news.param;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Date;

public class NewsParam {
    private int providerId;
    private String title;
    private String content;
    private String status;
    private Date postTime;
    private int articleLikeCount;
    private int categoryId;
    private int newsId;
    private int reviewerId;
    private String committerDescription;
    private String reviewerDescription;
    private String reviewStatus;
    private Date commitTime;
    private Date reviewTime;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getCommitterDescription() {
        return committerDescription;
    }

    public void setCommitterDescription(String committerDescription) {
        this.committerDescription = committerDescription;
    }

    public String getReviewerDescription() {
        return reviewerDescription;
    }

    public void setReviewerDescription(String reviewerDescription) {
        this.reviewerDescription = reviewerDescription;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    public String toString() {
        return "NewsParam{" +
                "providerId=" + providerId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", postTime=" + postTime +
                ", articleLikeCount=" + articleLikeCount +
                ", categoryId=" + categoryId +
                ", newsId=" + newsId +
                ", reviewerId=" + reviewerId +
                ", committerDescription='" + committerDescription + '\'' +
                ", reviewerDescription='" + reviewerDescription + '\'' +
                ", reviewStatus='" + reviewStatus + '\'' +
                ", commitTime=" + commitTime +
                ", reviewTime=" + reviewTime +
                '}';
    }
}

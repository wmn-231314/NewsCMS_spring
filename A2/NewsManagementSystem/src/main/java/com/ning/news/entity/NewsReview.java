package com.ning.news.entity;

import com.ning.news.param.NewsParam;

import java.util.Date;

public class NewsReview {
    private int id;
    private int newsId;
    private int reviewerId;
    private String committerDescription;
    private String reviewerDescription;
    private String status;
    private Date commitTime;
    private Date reviewTime;

    public NewsReview() {
    }

    public NewsReview(NewsParam newsParam){
        if (newsParam.getNewsId() > 0) this.newsId=newsParam.getNewsId();
        if (newsParam.getReviewerId() > 0) this.reviewerId=newsParam.getReviewerId();
        if (newsParam.getCommitterDescription() != null) this.committerDescription=newsParam.getCommitterDescription();
        if (newsParam.getReviewerDescription() != null) this.reviewerDescription=newsParam.getReviewerDescription();
        if (newsParam.getReviewStatus() != null) this.status=newsParam.getReviewStatus();
        if (newsParam.getCommitTime() != null) this.commitTime=newsParam.getCommitTime();
        if (newsParam.getReviewTime() != null) this.reviewTime=newsParam.getReviewTime();
    }

    public NewsReview(int newsId, int reviewerId, String committerDescription, String status, Date commitTime) {
        this.newsId = newsId;
        this.reviewerId = reviewerId;
        this.committerDescription = committerDescription;
        this.status = status;
        this.commitTime = commitTime;
    }

    public NewsReview(int newsId, int reviewerId, String status, Date commitTime) {
        this.newsId = newsId;
        this.reviewerId = reviewerId;
        this.status = status;
        this.commitTime = commitTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "NewsReview{" +
                "id=" + id +
                ", newsId=" + newsId +
                ", reviewerId=" + reviewerId +
                ", committerDescription='" + committerDescription + '\'' +
                ", reviewerDescription='" + reviewerDescription + '\'' +
                ", status='" + status + '\'' +
                ", commitTime=" + commitTime +
                ", reviewTime=" + reviewTime +
                '}';
    }
}

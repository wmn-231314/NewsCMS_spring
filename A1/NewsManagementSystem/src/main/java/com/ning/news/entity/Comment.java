package com.ning.news.entity;

import java.util.Date;

public class Comment {
    private int id;
    private int newsId;
    private int userId;
    private String content;
    private Date commitTime;

    public Comment() {
    }

    public Comment(int newsId, int userId, String content, Date commitTime) {
        this.newsId = newsId;
        this.userId = userId;
        this.content = content;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", news_id=" + newsId +
                ", user_id=" + userId +
                ", content='" + content + '\'' +
                ", commitTime=" + commitTime +
                '}';
    }
}

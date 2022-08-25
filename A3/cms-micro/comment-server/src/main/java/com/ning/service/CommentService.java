package com.ning.service;

import com.ning.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByNews(int nid);
    Comment getCommentByUserNews(int uid, int nid);

    int addComment(Comment comment);

    int updateComment(Comment comment);

    int deleteCommentByNews(int nid);
    int deleteCommentById(int id);
    int deleteCommentByUser(int uid);
    int deleteCommentByUserNews(int uid, int nid);
}

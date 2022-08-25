package com.ning.service.impl;

import com.ning.entity.Comment;
import com.ning.mapper.CommentMapper;
import com.ning.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;

    @Override
    public List<Comment> getCommentByNews(int nid) {
        return commentMapper.getCommentByNews(nid);
    }

    @Override
    public Comment getCommentByUserNews(int uid, int nid) {
        return commentMapper.getCommentByUserNews(uid,nid);
    }

    @Override
    public int addComment(Comment comment) {
        return commentMapper.addComment(comment);
    }

    @Override
    public int updateComment(Comment comment) {
        return commentMapper.updateComment(comment);
    }

    @Override
    public int deleteCommentByNews(int nid) {
        return commentMapper.deleteCommentByNews(nid);
    }

    @Override
    public int deleteCommentById(int id) {
        return commentMapper.deleteCommentById(id);
    }

    @Override
    public int deleteCommentByUser(int uid) {
        return commentMapper.deleteCommentByUser(uid);
    }

    @Override
    public int deleteCommentByUserNews(int uid, int nid) {
        return commentMapper.deleteCommentByUserNews(uid,nid);
    }
}

package com.ning.mapper;

import com.ning.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    List<Comment> getCommentByNews(int nid);
    Comment getCommentByUserNews(@Param("uid") int uid,@Param("nid") int nid);

    int addComment(Comment comment);

    int updateComment(Comment comment);

    int deleteCommentByNews(int nid);
    int deleteCommentById(int id);
    int deleteCommentByUser(int uid);
    int deleteCommentByUserNews(@Param("uid") int uid,@Param("nid") int nid);
}

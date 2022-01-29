package com.blog.springblog.service;

import com.blog.springblog.entity.Comment;

import java.util.List;

public interface CommentService {

    public void saveComment(Long postId, Comment comment);

    List<Comment> getCommentByPostId(long postId);

    Comment getCommentByIdAndPostId(Long commentId, Long postId);

    List<Comment> getAllCommentByPostId(long postId);
}

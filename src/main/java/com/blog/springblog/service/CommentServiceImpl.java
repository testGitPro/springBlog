package com.blog.springblog.service;

import com.blog.springblog.entity.Comment;
import com.blog.springblog.repository.CommentRepository;
import com.blog.springblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements  CommentService{
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;



    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }


    @Override
    public void saveComment(Long postId, Comment comment) {
        LocalDateTime now = LocalDateTime.now();
        comment.setCreationDate(now);
        comment.setPost(postRepository.getById(postId));
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentByPostId(long postId) {
        return commentRepository.findAllByPost_PostId(postId);

    }

   @Override
    public Comment getCommentByIdAndPostId(Long postId, Long commentId) {
      return commentRepository.getByCommentIdAndPost_PostId(postId, commentId);
    }

    @Override
    public List<Comment> getAllCommentByPostId(long postId) {
        return commentRepository.findAllByPost_PostId(postId);
    }
}

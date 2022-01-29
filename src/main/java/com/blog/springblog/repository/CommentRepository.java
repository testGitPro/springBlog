package com.blog.springblog.repository;

import com.blog.springblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPost_PostId(long postId);

    Comment getByCommentIdAndPost_PostId(long id, long postId);

}

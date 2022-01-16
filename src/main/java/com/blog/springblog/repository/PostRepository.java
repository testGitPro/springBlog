package com.blog.springblog.repository;

import com.blog.springblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    public Post findByTitle(String title);
    public List<Post> findByTitleIgnoreCase(String title);

    List<Post> findByStar(boolean b);
}

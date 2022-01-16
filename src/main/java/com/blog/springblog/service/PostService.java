package com.blog.springblog.service;

import com.blog.springblog.entity.Post;
import com.blog.springblog.error.PostNotFoundException;

import java.util.List;

public interface PostService {
    public Post savePost(Post post);

    public List<Post> fetchPostList();

    public Post fetchPostById(Long postId) throws PostNotFoundException;

    public void deletePostById(Long postId);

    public Post updatePostById(Long postId, Post post);

    public List<Post> fetchPostByTitle(String title);

    public List<Post> sortPostByTitle();

    public List<Post> fetchPostByStar(boolean b);

    public Post updatePostAddStar(Long postId, Post post);

    public void deleteStar(Long postId);
}

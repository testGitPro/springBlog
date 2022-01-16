package com.blog.springblog.service;

import com.blog.springblog.entity.Post;
import com.blog.springblog.error.PostNotFoundException;
import com.blog.springblog.repository.PostRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> fetchPostList() {
        return postRepository.findAll();
    }

    @Override
    public Post fetchPostById(Long postId) throws PostNotFoundException {
        Optional<Post> post = postRepository.findById(postId);
        if(!post.isPresent()) {
            throw new PostNotFoundException("Post Not Available");
        }
        return post.get();
    }

    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public Post updatePostById(Long postId, Post post) {
        Post postDB = postRepository.findById(postId).get();
        if (Objects.nonNull(post.getTitle()) && !"".equalsIgnoreCase(post.getTitle())) {
            postDB.setTitle(post.getTitle());
         }
        if (Objects.nonNull(post.getContent()) && !"".equalsIgnoreCase(post.getContent())) {
            postDB.setContent(post.getContent());
        }
        return postRepository.save(postDB);
    }

    @Override
    public List<Post> fetchPostByTitle(String title) {
        return postRepository.findByTitleIgnoreCase(title);
    }

    @Override
    public List<Post> sortPostByTitle() {
        return postRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }

    @Override
    public List<Post> fetchPostByStar(boolean b) {
        return postRepository.findByStar(b);
    }

    @Override
    public Post updatePostAddStar(Long postId, Post post) {
        Post postDB = postRepository.findById(postId).get();
        postDB.setStar(true);
        return postRepository.save(postDB);
    }

    @Override
    public void deleteStar(Long postId) {
        Post post = postRepository.getById(postId);
        post.setStar(false);
        postRepository.save(post);
    }


}

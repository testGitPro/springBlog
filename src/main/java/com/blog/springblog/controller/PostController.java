package com.blog.springblog.controller;

import com.blog.springblog.entity.Comment;
import com.blog.springblog.entity.Post;
import com.blog.springblog.error.PostNotFoundException;
import com.blog.springblog.service.CommentService;
import com.blog.springblog.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    private final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

    @PostMapping("/api/v1/posts")
    public Post savePost(@Valid @RequestBody Post post) {
        //PostService service = new PostServiceImpl();  //Autowired private  PostService postService;
        LOGGER.trace("Inside savePost of PostController{}", post.toString());
        return postService.savePost(post);

    }

    @GetMapping("/api/v1/posts/{id}")
    public Post fetchPostById(@PathVariable("id") Long postId) throws PostNotFoundException {
        LOGGER.trace("Inside fetchPostById of PostController. Fetch Post id -" + postId);
        return postService.fetchPostById(postId);

    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void deletePostById(@PathVariable("id") Long postId) {
        postService.deletePostById(postId);
        LOGGER.trace("Inside deletePostById of PostController. Edit Post id -" + postId);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Post updatePostById(@PathVariable("id") Long postId, @RequestBody Post post) {
        LOGGER.trace("Inside updatePostById of PostController. Edit Post id -" + postId);
        return postService.updatePostById(postId, post);
    }

    @GetMapping("/api/v1/posts")
    public List<Post> fetchPostList(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "sort", required = false) String sort) {
        List<Post> posts;
        if (title != null) {
            posts = postService.fetchPostByTitle(title);
        } else if (sort != null) {
            posts = postService.sortPostByTitle();
        } else {
            posts = postService.fetchPostList();
        }
        LOGGER.trace("Inside fetchPostList of PostController {}", posts);
        return posts;
    }

    @GetMapping("/api/v1/posts/star")
    public List<Post> fetchPostListwittStar() {
        List<Post> posts;
        posts = postService.fetchPostByStar(true);
        LOGGER.trace("Inside fetchPostList of PostController {}", posts);
        return posts;
    }

    @PutMapping("/api/v1/posts/{id}/star")
    public Post updatePostAddStar(@PathVariable("id") Long postId, @RequestBody Post post) {
        LOGGER.trace("Inside updatePostAddStar of PostController. Edit Post id -" + postId);
        return postService.updatePostAddStar(postId, post);
    }

    @DeleteMapping("/api/v1/posts/{id}/star")
    public void deleteStar(@PathVariable("id") Long postId) {
        postService.deleteStar(postId);
        LOGGER.trace("Inside deleteStar of PostController. Removed Star. Post id -" + postId);
    }

    @PostMapping("/api/v1/posts/{id}/comments")
    public void addComment(@PathVariable("id") long postId,
                           @RequestBody Comment comment) {
        //PostService service = new PostServiceImpl();  //Autowired private  PostService postService;
        LOGGER.trace("Inside addComment of PostController{}", comment.toString());
        commentService.saveComment(postId, comment);

    }


    @GetMapping("/api/v1/posts/{id}/comments")
    public List<Comment> getComment(@Valid @PathVariable("id") long postId) {

        return commentService.getCommentByPostId(postId);

    }

    @GetMapping("/api/v1/posts/{postId}/comments/{commentId}")
    public Comment getCommentByIdAndPostId(@PathVariable("postId") long postId,
                                           @PathVariable("commentId") long commentId) {

        return commentService.getCommentByIdAndPostId(commentId, postId);

    }

    @GetMapping("/api/v1/posts/{postId}/full")
    public List<Comment> getAllCommentByPostId(@PathVariable("postId") long postId) {

        return commentService.getAllCommentByPostId(postId);

    }

}
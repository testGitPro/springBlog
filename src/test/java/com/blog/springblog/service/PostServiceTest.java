package com.blog.springblog.service;

import com.blog.springblog.entity.Post;
import com.blog.springblog.repository.PostRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private
    PostService postService;

    @MockBean
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        List<Post> posts = new ArrayList<>();
        Post post = Post.builder()
                .title("Title")
                .content("Some content")
                .build();
        posts.add(0,post);

        Mockito.when(postRepository.findByTitleIgnoreCase("Title")).thenReturn(posts);
    }

    @Test
    public void ifValidTitle_thenTitleShouldFound(){
        String title = "Title";
        List<Post> found = postService.fetchPostByTitle(title);
        assertEquals(title, found.get(0).getTitle());


    }
}
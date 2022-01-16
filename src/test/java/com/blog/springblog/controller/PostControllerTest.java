package com.blog.springblog.controller;

import com.blog.springblog.entity.Post;
import com.blog.springblog.error.PostNotFoundException;
import com.blog.springblog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private Post post;

    @BeforeEach
    void setUp() {
         post = Post.builder()
                .title("Title")
                .content("Some content")
                .build();
    }

    @Test
    void savePost() throws Exception {
        Post inputPost = Post.builder()
                .title("sfd")
                .content("1")
                .build();


        Mockito.when(postService.savePost(inputPost)).thenReturn(post);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"content\": \"1\",\n" +
                        "    \"title\": \"d\"\n" +
                        "\n" +
                        "}")).andExpect(status().isOk());
    }

    @Test
    void fetchPostById() throws Exception {
        Mockito.when(postService.fetchPostById(1L))
                .thenReturn(post);


       mockMvc.perform(get("/api/v1/posts/1")
            .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
              .andExpect(jsonPath("$.title").
               value(post.getTitle()));


    }
    }

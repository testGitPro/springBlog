package com.blog.springblog.repository;

import com.blog.springblog.entity.Post;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private  PostRepository postRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {

        Post post = Post.builder()
                .title("TitleTest")
                .content("Some content")
                .build();
        entityManager.persist(post);
    }

    @Test
    public  void whenFindById_thenReturnPost() {
        Post post = postRepository.findById(1L).get();
        assertEquals(post.getTitle(),"TitleTest");
    }
}
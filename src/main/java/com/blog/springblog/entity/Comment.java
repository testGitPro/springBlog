package com.blog.springblog.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "comment_sequence")
    private Long commentId;
    private String text;
    private LocalDateTime creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post",
            referencedColumnName = "postId")
    private Post post;

    @JsonProperty("post")
    public long getPostId() {
        return post.getPostId();
    }


}


package com.blog.springblog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Post {

    @Id
    @SequenceGenerator(name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "post_sequence")
    private Long postId;

    @NotBlank(message = "Please add title")
    private String title;

    @Length(max = 2000)
    @NotBlank(message = "Please add content")
    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean star;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post", referencedColumnName = "postId")



    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Comment> comments;
}
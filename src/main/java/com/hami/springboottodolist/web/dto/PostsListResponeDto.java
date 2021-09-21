package com.hami.springboottodolist.web.dto;

import com.hami.springboottodolist.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponeDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsListResponeDto(Posts entity){
        id = entity.getId();
        title = entity.getTitle();
        content = entity.getContent();
        author = entity.getAuthor();
    }
}

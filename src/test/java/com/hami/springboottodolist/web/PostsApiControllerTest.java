package com.hami.springboottodolist.web;

import com.hami.springboottodolist.domain.posts.Posts;
import com.hami.springboottodolist.domain.posts.PostsRepository;
import com.hami.springboottodolist.web.dto.PostsSaveRequestDto;
import com.hami.springboottodolist.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postRepository;

    @AfterEach
    public void tearDown() throws Exception{
        postRepository.deleteAll();
    }

    @Test
    public void Posts_save() throws Exception{
        //given
        String title = "title";
        String content = "content";
        String author = "author";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder().title(title).content(content).author(author).build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
    }

    @Test
    public void posts_update() throws Exception{
        //given
        String title1 = "title1";
        String content1 = "content1";
        String author = "author";

         Posts posts = postRepository.save(Posts.builder().title(title1).content(content1).author(author).build());

        String title2 = "title2";
        String content2 = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(title2).content(content2).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" +posts.getId();

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

         //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title2);
        assertThat(all.get(0).getContent()).isEqualTo(content2);
    }
}

package com.hami.springboottodolist.service.posts;

import com.hami.springboottodolist.domain.posts.Posts;
import com.hami.springboottodolist.domain.posts.PostsRepository;
import com.hami.springboottodolist.web.dto.PostsListResponeDto;
import com.hami.springboottodolist.web.dto.PostsResponeDto;
import com.hami.springboottodolist.web.dto.PostsSaveRequestDto;
import com.hami.springboottodolist.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<PostsListResponeDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponeDto::new).collect(Collectors.toList());
    }

    public PostsResponeDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        return new PostsResponeDto(entity);
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다."));
        postsRepository.delete(entity);
    }
}

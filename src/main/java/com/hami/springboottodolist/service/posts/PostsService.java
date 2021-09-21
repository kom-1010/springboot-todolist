package com.hami.springboottodolist.service.posts;

import com.hami.springboottodolist.domain.posts.PostsRepository;
import com.hami.springboottodolist.web.dto.PostsListResponeDto;
import com.hami.springboottodolist.web.dto.PostsSaveRequestDto;
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

    public List<PostsListResponeDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponeDto::new).collect(Collectors.toList());
    }
}

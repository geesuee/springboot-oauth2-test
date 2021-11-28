package com.geesuee.springbootstudy.service.posts;

import com.geesuee.springbootstudy.web.domain.posts.Posts;
import com.geesuee.springbootstudy.web.domain.posts.PostsRepository;
import com.geesuee.springbootstudy.web.dto.PostsListResponseDto;
import com.geesuee.springbootstudy.web.dto.PostsResponseDto;
import com.geesuee.springbootstudy.web.dto.PostsSaveRequestDto;
import com.geesuee.springbootstudy.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor       // final 이 선언된 모든 필드를 인자값으로 받는 생성자 생성 -> Bean 주입 방식 (권장)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}

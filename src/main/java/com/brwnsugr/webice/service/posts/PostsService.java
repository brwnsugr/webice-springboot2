package com.brwnsugr.webice.service.posts;

import com.brwnsugr.webice.domain.posts.Posts;
import com.brwnsugr.webice.domain.posts.PostsRepository;
import com.brwnsugr.webice.web.dto.PostsSaveRequestDto;
import com.brwnsugr.webice.web.dto.PostsUpdateRequestDto;
import com.brwnsugr.webice.web.dto.PostsResponseDto;
import com.brwnsugr.webice.web.dto.PostsListResponseDto;
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
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readonly 옵션을주면, 조회기능만 남겨둬서 조회 속도가 개선됨(즉, 등록, 수정, 삭제 기능이 전혀 없는 메소드에 이걸 쓰자)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) // postrepo 결과로 넘어온 Posts의 stream을 map을 통해 postslistresponsedto로
                .collect(Collectors.toList()); //이걸 list로 변환
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts); // JpaRepository 에서 이미 delete 메소드를 지원하고 있음..
    }
}

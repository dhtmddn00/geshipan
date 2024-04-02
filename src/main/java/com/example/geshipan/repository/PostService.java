package com.example.geshipan.repository;

import com.example.geshipan.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    // 생성자를 통한 의존성 주입
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return postRepository.findAll(pageable);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}

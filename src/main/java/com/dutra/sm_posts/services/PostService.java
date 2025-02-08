package com.dutra.sm_posts.services;

import com.dutra.sm_posts.models.dtos.PostDto;
import com.dutra.sm_posts.models.dtos.UserDto;
import com.dutra.sm_posts.models.entities.Post;
import com.dutra.sm_posts.models.entities.User;
import com.dutra.sm_posts.repositories.PostRepository;
import com.dutra.sm_posts.repositories.UserRepository;
import com.dutra.sm_posts.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id) {
        return getPostById(id);
    }

    public List<PostDto> findByTitle(String text) {
        return postRepository.findByTitleContainingIgnoreCase(text)
                .stream().map(PostDto::new).toList();
    }

    private Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found."));
    }


}

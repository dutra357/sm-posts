package com.dutra.sm_posts.services;

import com.dutra.sm_posts.models.dtos.PostDto;
import com.dutra.sm_posts.models.dtos.UserDto;
import com.dutra.sm_posts.models.entities.Post;
import com.dutra.sm_posts.models.entities.User;
import com.dutra.sm_posts.repositories.PostRepository;
import com.dutra.sm_posts.repositories.UserRepository;
import com.dutra.sm_posts.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
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

    public List<PostDto> fullSearch(String text, String start, String end) {
        Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
        Instant endMoment = convertMoment(end, Instant.now());

        return postRepository.fullSearch(text, startMoment, endMoment)
                .stream().map(PostDto::new).toList();
    }

    private Instant convertMoment(String originalString, Instant alternative) {
        try {
            return Instant.parse(originalString);
        } catch (DateTimeParseException e) {
            return alternative;
        }

    }

    private Post getPostById(String id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not found."));
    }


}

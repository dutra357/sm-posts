package com.dutra.sm_posts.controllers;

import com.dutra.sm_posts.models.dtos.PostDto;
import com.dutra.sm_posts.models.dtos.UserDto;
import com.dutra.sm_posts.models.entities.Post;
import com.dutra.sm_posts.services.PostService;
import com.dutra.sm_posts.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "posts")
public class PostController {

    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<PostDto>> findByTitle(@RequestParam(value = "") String text) {
        return ResponseEntity.ok(postService.findByTitle(text));
    }

}

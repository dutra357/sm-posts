package com.dutra.sm_posts.models.dtos;

import com.dutra.sm_posts.models.embedded.Author;
import com.dutra.sm_posts.models.embedded.Comment;
import com.dutra.sm_posts.models.entities.Post;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PostDto {

    private String id;
    private Instant moment;
    private String title;
    private String body;
    private Author author;
    private final List<Comment> comments = new ArrayList<>();

    public PostDto() {}
    public PostDto(String id, Instant moment, String title, String body, Author author) {
        this.id = id;
        this.moment = moment;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public PostDto(Post post) {
        this.id = post.getId();
        this.moment = post.getMoment();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.author = post.getAuthor();

        this.comments.addAll(post.getComments());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }
}

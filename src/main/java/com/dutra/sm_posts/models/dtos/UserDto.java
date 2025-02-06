package com.dutra.sm_posts.models.dtos;

import com.dutra.sm_posts.models.entities.User;

public class UserDto {

    private String id;
    private String name;
    private String email;

    public UserDto() {}
    public UserDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

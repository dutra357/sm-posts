package com.dutra.sm_posts.services;

import com.dutra.sm_posts.models.dtos.PostDto;
import com.dutra.sm_posts.models.dtos.UserDto;
import com.dutra.sm_posts.models.entities.User;
import com.dutra.sm_posts.repositories.UserRepository;
import com.dutra.sm_posts.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }

    public UserDto findById(String id) {
        return new UserDto(getEntityById(id));
    }

    public List<PostDto> getUserPosts(String id) {
        User user = getEntityById(id);
        return user.getPosts().stream().map(PostDto::new).toList();
    }

    public UserDto insertUser(UserDto userDto) {
        User user = new User();
        copyDtoToEntity(userDto, user);

        return new UserDto(userRepository.insert(user));
    }

    public UserDto updateUser(String id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found."));

        copyDtoToEntity(userDto, user);

        return new UserDto(userRepository.save(user));
    }

    public void deleteUser(String id) {
        getEntityById(id);
        userRepository.deleteById(id);
    }

    private void copyDtoToEntity(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
    }

    private User getEntityById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found."));
    }
}

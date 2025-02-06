package com.dutra.sm_posts.services;

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
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found."));
        return new UserDto(user);
    }

    public UserDto insertUser(UserDto userDto) {
        User user = new User();
        copyDtoToEntity(userDto, user);

        return new UserDto(userRepository.insert(user));
    }

    private void copyDtoToEntity(UserDto userDto, User user) {
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
    }
}

package com.dutra.sm_posts.config;

import com.dutra.sm_posts.models.entities.User;
import com.dutra.sm_posts.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig {

    private final UserRepository userRepository;
    public TestConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User joao = new User(null, "Joao Rosa", "joao@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, joao));
    }
}

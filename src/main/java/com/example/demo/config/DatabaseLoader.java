package com.example.demo.config;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // == create initial user
        createUserIfNotFound("alejandro", "Test", "ROLE_ADMIN");
        createUserIfNotFound("gonzalo", "Test", "ROLE_USER");

        alreadySetup = true;
    }

    @Transactional
    UserModel createUserIfNotFound(final String username, final String password, String role) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            user = new UserModel();
            user.setUsername(username);
            user.setPassword(password); //Encode
            user.setRole(role);
        }

        user = userRepository.save(user);
        return user;
    }
}

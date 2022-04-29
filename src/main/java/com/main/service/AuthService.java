package com.main.service;

import com.main.model.UserModel;
import com.main.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserModel response = authRepository.validateLogin(username);

        if (response.getUsername() == null) {
            throw new UsernameNotFoundException(username);
        }

        UserDetails user = User.withUsername(response.getUsername()).password(response.getPassword()).authorities("USER").build();
        return user;
    }
}

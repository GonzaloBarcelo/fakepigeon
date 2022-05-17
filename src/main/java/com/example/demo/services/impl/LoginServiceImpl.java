package com.example.demo.services.impl;


import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.APP_ROLES;
import com.example.demo.services.LoginService;
import com.example.demo.services.LoginServiceResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class LoginServiceImpl implements LoginService {

    Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginServiceResult authenticate(String user, String password) {

        UserModel user1 = userRepository.findByUsername(user);

        //logger.info("User : {}", user1.toString());

        if(password.equals(user1.getPassword())) {
            String value = user1.getUsername() + ":" + user1.getPassword();
            String access_token = Base64.getEncoder().encodeToString(value.getBytes());
            return new LoginServiceResult(true, access_token);
        } else {
            return new LoginServiceResult(false);
        }
    }

    @Override
    public APP_ROLES getRole(String accessToken) {
        String access_token_raw = accessToken.replace("Bearer ", "");
        String access_token = new String(Base64.getDecoder().decode(access_token_raw));
        logger.info("Access token raw: " + access_token_raw);
        String[] parts = access_token.split(":");

        UserModel user1 = userRepository.findByUsername(parts[0]);
        return APP_ROLES.get(user1.getRole());
    }
}

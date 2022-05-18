package com.example.demo.controller;

import com.example.demo.services.APP_ROLES;
import com.example.demo.services.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    Logger logger = LoggerFactory.getLogger(SecureController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/secure", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> greeting(@RequestHeader("Authorization") String token) {
        APP_ROLES role = loginService.getRole(token);
        if (APP_ROLES.ROLE_ADMIN.equals(role)){
            return ResponseEntity.ok().body("Safe place");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("");
    }

}


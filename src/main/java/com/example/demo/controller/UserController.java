package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/register")
    public ResponseEntity<HashMap<UserModel,Boolean>> addUser(@RequestBody UserModel user){
        System.out.println("Añadiendo usuario a la base de datos...");
        user.setRole("ROLE_USER");
        HashMap<UserModel,Boolean> status = new HashMap<>();
        status.put(user,userService.userRegister(user));
        return ResponseEntity.ok().body(status);

    }
}

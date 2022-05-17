package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
/*
    @PostMapping(path="/login")
    public ResponseEntity<HashMap<UserModel,Boolean>> login(@RequestBody UserModel user){
        System.out.println("Buscando login...");
        System.out.println("El usuario introducido es:");
        System.out.println("Nombre:"+user.getUsername());
        System.out.println("Password:"+user.getPassword());
        System.out.println("--------------------------");

        HashMap<UserModel,Boolean> status = new HashMap<>();
        status.put(user,userService.userLogin(user));
        return ResponseEntity.ok().body(status);
    }

 */
/*
    @GetMapping("/allUsers")
    public ResponseEntity<HashMap<Integer, ArrayList<String>>> getAllUsersNames(){
        HashMap<Integer, ArrayList<String>> status= new HashMap<>();
        List<UserModel> allUsers = userService.getAllUsers();
        ArrayList<String> us= new ArrayList<>();
        Integer i=0;
        for (UserModel u:allUsers){
            us.add(u.getUsername());
            i++;
        }
        status.put(i,us);
        return ResponseEntity.ok().body(status);
    }

    @PostMapping(path="/register")
    public ResponseEntity<HashMap<UserModel,Boolean>> addUser(@RequestBody UserModel user){
        System.out.println("Añadiendo usuario a la base de datos...");
        HashMap<UserModel,Boolean> status = new HashMap<>();
        status.put(user,userService.userRegister(user));
        return ResponseEntity.ok().body(status);

    }


 */
}

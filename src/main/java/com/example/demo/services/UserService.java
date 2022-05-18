package com.example.demo.services;

import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService{

    @Autowired
    private UserRepository userRepository;

/*
    public List<UserModel> getAllUsers(){
        return userRepository.getAllUsers();
    }



    public boolean userLogin(UserModel userC){
        ArrayList<UserModel> users= (ArrayList<UserModel>) userRepository.getAllUsers();
        System.out.println("-----Printing all users-----");
        for(UserModel u:users){
            System.out.println(u.getUsername()+"---"+u.getPassword());
        }
        System.out.println("----------------------------");

        for (UserModel user:users){
            if (user.equals(userC)){
                System.out.println("Se ha logeado con exito el usuario: "+userC.getUsername());
                return true;
            }
        }
        System.out.println("No existe ningun usuario con ese username.");
        return false;
    }

    public boolean userRegister(UserModel userToAdd){
        if (isInUsers(userToAdd)){
            return false;
        }
        userRepository.save(userToAdd);
        System.out.println("Se ha añadido el usuario: "+userToAdd.getUsername());
        return true;

    }

    public boolean isInUsers(UserModel user){
        ArrayList<UserModel> users= (ArrayList<UserModel>) userRepository.getAllUsers();
        for (UserModel userI:users){
            if (user.getUsername().equals(userI.getUsername())){
                System.out.println("En la base de datos ya existe el username: "+user.getUsername());
                return true;
            }
        }
        return false;
    }
    */


}

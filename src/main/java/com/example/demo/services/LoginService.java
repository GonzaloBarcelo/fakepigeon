package com.example.demo.services;

public interface LoginService {

    LoginServiceResult authenticate(String user, String password);

    APP_ROLES getRole(String accessToken);
}
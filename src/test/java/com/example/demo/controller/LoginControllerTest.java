package com.example.demo.controller;

import com.example.demo.services.LoginService;
import com.example.demo.services.LoginServiceResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    LoginService loginService;

    @Test
    void loginko2() {
        LoginRequest request= new LoginRequest("gonzalo","admin");
        LoginServiceResult loginServiceResult = loginService.authenticate(request.getUser(), request.getPassword());
        String token = loginServiceResult.getAccess_token();
        LoginResponse expectedResult= new LoginResponse(null,null);
        String url = "http://localhost:" + Integer.toString(port) + "/api/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("user", request.getUser());
        map.add("password", request.getPassword());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,headers);
        ResponseEntity<LoginResponse> result = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<LoginResponse>() {
        });

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals(expectedResult, result.getBody());
    }
    @Test
    void loginKo() {
        LoginRequest request= new LoginRequest("","admin");
        LoginServiceResult loginServiceResult = loginService.authenticate(request.getUser(), request.getPassword());
        String token = loginServiceResult.getAccess_token();
        LoginResponse expectedResult= new LoginResponse(null,token);
        String url = "http://localhost:" + Integer.toString(port) + "/api/login";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("user", request.getUser());
        map.add("password", request.getPassword());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map,headers);
        ResponseEntity<LoginResponse> result = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<LoginResponse>() {
        });


        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals(expectedResult, result.getBody());
    }
}
package com.example.demo.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginServiceResult {
    Boolean flag;
    String access_token;

    public LoginServiceResult(boolean flag) {
        this.flag = flag;
    }
}

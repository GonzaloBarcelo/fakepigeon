package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NonNull
    @NotEmpty
    private String user;

    @NotNull
    @NotEmpty
    //@Pattern(message="max 5 words please" , regexp="^[a-zA-Z-.0-9]{1,5}$")
    private String password;
}

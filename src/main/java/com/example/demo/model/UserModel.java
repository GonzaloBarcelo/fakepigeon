package com.example.demo.model;


import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@Table("USER")
public class UserModel {

    @Id
    @Column("ID")
    private Long id;
    @Column("USERNAME")
    private String username;
    @Column("PASSWORD")
    private String password;
    @Column("ROLE")
    private String role;


    public UserModel(Long id, String username, String password,String role) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role=role;

    }

    public UserModel() {
        super();
    }

}

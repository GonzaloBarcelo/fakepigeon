package com.example.demo.model;


import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("USERS")
public class UserModel implements Serializable {

    @Id
    @Column("USERNAME_ID")
    private long username_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column("USERNAME")
    private String username;
    @Column("PASSWORD")
    private String password;

    @Override
    public boolean equals(Object o){
        if (o instanceof UserModel){
            UserModel userC= (UserModel) o;
            if (userC.getUsername().equals(this.getUsername()) && userC.getPassword().equals(this.getPassword())){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}

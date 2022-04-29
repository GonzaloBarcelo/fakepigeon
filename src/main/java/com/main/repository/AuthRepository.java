package com.main.repository;

import com.main.model.User;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthRepository extends CrudRepository<String, User> {
    @Query("SELECT * FROM USERS WHERE USERNAME =: username, PASSWORD =: password") User validateLogin(@Param("username") String username, @Param("password") String password);
    @Query("INSERT INTO USERS VALUES(:username, :password)") Integer registerUser(@Param("username") String username, @Param("password") String password);
}

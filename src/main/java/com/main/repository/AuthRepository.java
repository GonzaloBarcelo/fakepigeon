package com.main.repository;

import com.main.model.UserModel;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends CrudRepository<String, UserModel> {
    @Query("SELECT * FROM USERS WHERE USERNAME =: username") UserModel validateLogin(@Param("username") String username);
    @Query("INSERT INTO USERS VALUES(:username, :password)") Integer registerUser(@Param("username") String username, @Param("password") String password);
}

package com.example.demo.repository;

import com.example.demo.model.UserModel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Repository
public interface UserRepository extends CrudRepository<UserModel,Long> {

    @Query("SELECT * FROM USER")
    public List<UserModel> getAllUsers();

    @Override
    void delete(UserModel user);

    UserModel findByUsername(String username);

}

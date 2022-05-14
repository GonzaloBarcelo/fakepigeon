package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.PrivateMessageModel;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateChatRepository extends CrudRepository<PrivateMessageModel, Long> {
    @Query("SELECT * FROM PRIVATE_MESSAGES WHERE SENDER = :loggedUser OR RECEIVER = :loggedUser") List<PrivateMessageModel> initialMessageLoad(@Param("loggedUser") String loggedUser);

    @Modifying
    @Query("INSERT INTO PRIVATE_MESSAGES (SENDER,RECEIVER,CONTENT) VALUES(:sender, :receiver, :content)")
    void storeMessage(@Param("sender") String sender, @Param("receiver") String receiver, @Param("content") String content);
}


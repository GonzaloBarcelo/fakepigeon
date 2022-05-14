package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.GlobalMessageModel;
import com.example.demo.model.GlobalMessageModel.MessageType;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalChatRepository extends CrudRepository<GlobalMessageModel, Long> {

    @Query("SELECT * FROM GLOBAL_MESSAGES") List<GlobalMessageModel> initialMessageLoad();
    
    @Modifying
    @Query("INSERT INTO GLOBAL_MESSAGES (MESSAGE_TYPE,SENDER,CONTENT) VALUES(:messageType, :sender, :content)")
    void storeMessage(@Param("messageType") MessageType messageType, @Param("sender") String sender, @Param("content") String content);
}

package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.MessageTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageTable, Long> {
    List<MessageTable> findAllByFromUserOrToUser(MyUserTable fromUser, MyUserTable toUser);
    List<MessageTable> findAllByFromUserAndToUser(MyUserTable fromUser, MyUserTable toUser);
}

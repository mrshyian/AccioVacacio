package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.aws.database.models.MessageTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.userPage.webclients.MailBoxImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class MailBoxController {

    @Autowired
    MailBoxImpl mailBoxImpl;

    @PostMapping("/mail_to_friend/new_message_in_chat")
    public void newMessageInChat(@RequestBody String messageData ) {
        mailBoxImpl.addNewMessage(messageData);
    }

    @GetMapping("/mail_to_friend/all_chats")
    public List<MyUserTable> getAllPenFriends(){
        return mailBoxImpl.getAllPenFriends();
    }

    @GetMapping("/mail_to_friend/{toUser}")
    public void newChat(@PathVariable String toUser){
        mailBoxImpl.newChat(toUser);
    }

    @GetMapping("/mail_to_friend/messages/{toUser}")
    public List<MessageTable> getMessages(@PathVariable String toUser){
        return mailBoxImpl.getMessages(toUser);
    }

}

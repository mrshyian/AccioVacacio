package com.codecool.travelhelper.userPage.controllers;

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

    @PostMapping("/mail_to_friend")
    public void newMail(@RequestBody String mailData ) {
        mailBoxImpl.addNewMail(mailData);
    }

    @GetMapping("/mail_to_friend/all_chats")
    public List<MyUserTable> getAllPenFriends(){

        return mailBoxImpl.getAllPenFriends();
    }

}

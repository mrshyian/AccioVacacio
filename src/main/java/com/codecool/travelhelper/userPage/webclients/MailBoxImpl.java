package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MessageTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.MessageRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailBoxImpl {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginImpl loginImpl;

    public void addNewMail(String mailData){
        Long userId = loginImpl.getCurrentUserId();
        MyUserTable fromUser = userRepository.findAllById(userId);

        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(mailData);

        String mailText = commentJsonObject.get("mailText").getAsString();
        String mailTitle = commentJsonObject.get("mailTitle").getAsString();
        Long friendId = Long.parseLong(commentJsonObject.get("friendId").getAsString());

        MyUserTable toUser = userRepository.findAllById(friendId);

        System.out.println("from user id: " + fromUser.getId());
        System.out.println("to user id: " + toUser.getId());

        messageRepository.save(new MessageTable(mailTitle, mailText, fromUser, toUser));
    }

    public List<MyUserTable> getAllPenFriends(){
        List<MyUserTable> allPenFriends = new ArrayList<>();
        Long userId = loginImpl.getCurrentUserId();
        MyUserTable me = userRepository.findAllById(userId);

        List<MessageTable> allMyMails = messageRepository.findAllByFromUserAndToUser(me, me);

        for (MessageTable mail : allMyMails){
            if (!allPenFriends.contains(mail.getToUser())){
                allPenFriends.add(mail.getToUser());
            } else if (!allPenFriends.contains(mail.getFromUser())){
                allPenFriends.add(mail.getFromUser());
            }
        }
        return allPenFriends;
    }
}

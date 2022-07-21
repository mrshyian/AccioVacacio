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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        Long friendId = Long.parseLong(commentJsonObject.get("friendId").getAsString());

        MyUserTable toUser = userRepository.findAllById(friendId);

        System.out.println("from user id: " + fromUser.getId());
        System.out.println("to user id: " + toUser.getId());

        messageRepository.save(new MessageTable(mailText, fromUser, toUser));
    }

    public List<MyUserTable> getAllPenFriends(){
        List<MyUserTable> allPenFriends = new ArrayList<>();
        Long userId = loginImpl.getCurrentUserId();
        MyUserTable me = userRepository.findAllById(userId);

        List<MessageTable> allMyMails = messageRepository.findAllByFromUserOrToUser(me, me);

        for (MessageTable mail : allMyMails){
            if (!allPenFriends.contains(mail.getToUser())){
                allPenFriends.add(mail.getToUser());
            } else if (!allPenFriends.contains(mail.getFromUser())){
                allPenFriends.add(mail.getFromUser());
            }
        }
        return allPenFriends;
    }

    public void newChat(String toUserId) {
        Long userId = loginImpl.getCurrentUserId();
        MyUserTable fromUser = userRepository.findAllById(userId);
        MyUserTable toUser = userRepository.findAllById(Long.parseLong(toUserId));

        messageRepository.save(new MessageTable("Hello!", fromUser, toUser));
    }


    public List<MessageTable> getMessages(String toUserId){
        List<MessageTable> allMessages = new ArrayList<>();

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable fromUser = userRepository.findAllById(userId);
        MyUserTable toUser = userRepository.findAllById(Long.parseLong(toUserId));
        List<MessageTable> messagesToMe = messageRepository.findAllByFromUserAndToUser(fromUser, toUser);
        List<MessageTable> messagesFromMe = messageRepository.findAllByFromUserAndToUser(toUser, fromUser);

        allMessages.addAll(messagesToMe);
        allMessages.addAll(messagesFromMe);

        Collections.sort(allMessages);
        return allMessages;
    }
}

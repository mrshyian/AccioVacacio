package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.userPage.webclients.UserPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserPageController {

    @Autowired
    private UserPageImpl userPageImpl;

    @GetMapping("/usermainbar/{userId}")
    public MyUserTable getUser(@PathVariable String userId) {
        return userPageImpl.getUserFromDB(userId);
    }

    @PostMapping("/usermainbar")
    public void updateUser(@RequestBody String userData) {
        userPageImpl.updateUser(userData);
    }

    @GetMapping("/get_friend_by_nick/{userNickName}")
    public MyUserTable getFriendByNickName(@PathVariable String userNickName){
        return userPageImpl.getFriendByNickName(userNickName);
    }
}
